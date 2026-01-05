package com.fastgo.blockchain.blockchain.service;

import com.fastgo.blockchain.blockchain.dto.Nft.NftMetadataResponseDto;
import com.fastgo.blockchain.blockchain.dto.Pinata.PinataPayloadDto;
import com.fastgo.blockchain.blockchain.dto.Pinata.PinataResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PinataService {

    @Value("${pinata.jwt}")
    private String pinataJwt;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String PINATA_URL = "https://api.pinata.cloud/pinning/pinJSONToIPFS";

    public String uploadMetadataToIpfs(NftMetadataResponseDto metadata, String fileName) {
        try {
       
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + pinataJwt);

            
            PinataPayloadDto payload = new PinataPayloadDto(metadata, fileName);
            HttpEntity<PinataPayloadDto> request = new HttpEntity<>(payload, headers);

            
            PinataResponseDto response = restTemplate.postForObject(PINATA_URL, request, PinataResponseDto.class);
            if (response != null && response.getIpfsHash() != null) {
                // Ritorna l'hash nel formato standard IPFS
                return response.getIpfsHash();
            } else {
                throw new RuntimeException("Risposta vuota da Pinata");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore caricamento su Pinata: " + e.getMessage());
        }
    }
}