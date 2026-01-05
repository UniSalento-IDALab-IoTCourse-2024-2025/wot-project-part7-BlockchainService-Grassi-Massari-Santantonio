package com.fastgo.blockchain.blockchain.service;

import com.fastgo.blockchain.blockchain.domain.RiderBadge;
import com.fastgo.blockchain.blockchain.dto.Nft.BadgeResponseDto;
import com.fastgo.blockchain.blockchain.dto.Nft.NftAttributeDto;
import com.fastgo.blockchain.blockchain.dto.Nft.NftMetadataResponseDto;
import com.fastgo.blockchain.blockchain.domain.BadgeLevel;
import com.fastgo.blockchain.blockchain.repositories.RiderBadgeRepository;
import com.fastgo.blockchain.blockchain.wrappers.RiderBadge.TransferEventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BadgeService {

    @Autowired
    private RiderBadgeRepository badgeRepository;

    @Autowired
    private PinataService pinataService;

    @Value("${blockchain.rpc-url}")
    private String rpcUrl;

    @Value("${blockchain.private-key}")
    private String privateKey;

    @Value("${blockchain.nft-contract-address}")
    private String nftContractAddress;

    @Value("${blockchain.public-key}")
    private String systemWalletAddress;

    @Value("${fastgo.api.base-url}")
    private String apiBaseUrl;

    @Value("${fastgo.api.qr-endpoint}")
    private String qrEndpoint;

    private static final BigInteger NFT_GAS_LIMIT = BigInteger.valueOf(3_000_000); 
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);


    public void checkAndAssignBadges(String riderId, int totalPoints) {
        for (BadgeLevel level : BadgeLevel.values()) {
            // Se i punti sono sufficienti, prova ad assegnare il badge
            if (totalPoints >= level.getThreshold()) {
                assignBadgeToRider(riderId, level);
            }
        }
    }

  
    public RiderBadge assignBadgeToRider(String riderId, BadgeLevel level) {
        
        
        Optional<RiderBadge> existing = badgeRepository.findByRiderIdAndLevelAndActiveTrue(riderId, level);
        if (existing.isPresent()) {
            System.out.println("Badge " + level + " già presente per rider " + riderId);
            return existing.get(); // Ritorna quello esistente
        }

        System.out.println(">>> INIZIO PROCESSO NFT: Rider " + riderId + " -> " + level);

        try {

            String imageBase = "https://robohash.org/";

            String uniqueImageUrl = String.format("%s%s.png?set=set1&bgset=bg1&size=512x512", imageBase, riderId);

            List<NftAttributeDto> attributes = new ArrayList<>();
            attributes.add(new NftAttributeDto("Level", level.name()));
            attributes.add(new NftAttributeDto("Title", level.getLabel()));
            attributes.add(new NftAttributeDto("Rider ID", riderId));

            NftMetadataResponseDto metadata = new NftMetadataResponseDto(
                "FastGo " + level.name() + " Badge",
                "Badge ufficiale FastGo su IPFS. Livello: " + level.getLabel(),
                uniqueImageUrl,
                attributes
            );


            System.out.println("...Caricamento metadati su IPFS (Pinata)...");
            String fileName = "Badge_" + level.name() + "_" + riderId;
            String ipfsHash = pinataService.uploadMetadataToIpfs(metadata, fileName);
            
            System.out.println(">>> IPFS Hash ricevuto: " + ipfsHash);

            String tokenUri = "ipfs://" + ipfsHash;



            com.fastgo.blockchain.blockchain.wrappers.RiderBadge contract = loadContract();


            // MINT su Blockchain
            TransactionReceipt receipt = contract.safeMint(
                systemWalletAddress, 
                tokenUri
            ).send();

            if (!receipt.isStatusOK()) {
                throw new RuntimeException("Transazione NFT fallita (Reverted)!");
            }

            // Token ID
            String tokenId = "UNKNOWN";
            if (!contract.getTransferEvents(receipt).isEmpty()) {
                TransferEventResponse event = contract.getTransferEvents(receipt).get(0);
                tokenId = event.tokenId.toString();
            }

            // Salvataggio locale
            RiderBadge newBadge = new RiderBadge(
                riderId,
                level,
                tokenId,
                receipt.getTransactionHash(),
                nftContractAddress,
                tokenUri
            );
            
            badgeRepository.save(newBadge);
            System.out.println(">>> SUCCESSO! Token ID: " + tokenId);
            
            return newBadge;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il Minting: " + e.getMessage());
        }
    }

    private com.fastgo.blockchain.blockchain.wrappers.RiderBadge loadContract() {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);
        return com.fastgo.blockchain.blockchain.wrappers.RiderBadge.load(
                nftContractAddress,
                web3j,
                credentials,
                new StaticGasProvider(GAS_PRICE, NFT_GAS_LIMIT)
        );
    }

    public List<BadgeResponseDto> getBadgesForRider(String riderId) {
        
       
        List<RiderBadge> badges = badgeRepository.findByRiderIdAndActiveTrue(riderId);

       
        return badges.stream().map(badge -> {
            
            String originalUri = badge.getTokenUri();
            String publicUrl = null;

            if (originalUri != null && originalUri.startsWith("ipfs://")) {
                publicUrl = originalUri.replace("ipfs://", "https://" + qrEndpoint + "/ipfs/");
            } else {
                publicUrl = originalUri; 
            }

            return new BadgeResponseDto(
                badge.getLevel().name(),
                badge.getTokenId(),
                publicUrl,      
                originalUri,   
                badge.getAssignedAt()
            );
        }).collect(Collectors.toList());
    }
}