package com.fastgo.blockchain.blockchain.service;

import com.fastgo.blockchain.blockchain.domain.OrderBlockchain;
import com.fastgo.blockchain.blockchain.dto.OrderBlockchainResponseDto;
import com.fastgo.blockchain.blockchain.dto.OrderRegistrationDto;
import com.fastgo.blockchain.blockchain.repositories.BlockchainRepository;
import com.fastgo.blockchain.blockchain.wrappers.DeliveryStorage; // Il wrapper che hai generato
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class BlockchainService {

    @Autowired
    private BlockchainRepository blockchainRepository;

    @Autowired
    private OrderConverter converter;

    @Value("${blockchain.rpc-url}")
    private String rpcUrl;

    @Value("${blockchain.private-key}")
    private String privateKey;

    @Value("${blockchain.contract-address}")
    private String contractAddress;

    // Configurazioni Gas 
    // GAS_LIMIT: Quanto lavoro massimo può fare la transazione 
    // GAS_PRICE: Quanto si paga per unità 
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(300_000);
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L); // 20 Gwei

    /**
     * Scrive un ordine sulla Blockchain
     */
    public OrderBlockchainResponseDto registerOrder(OrderRegistrationDto request) {

        // Salvataggio iniziale (Stato Pending)
        OrderBlockchain orderEntity = converter.toEntity(request);
        orderEntity = blockchainRepository.save(orderEntity);

        try {
            // CONNESSIONE WEB3J
            DeliveryStorage contract = loadContract();

            // PREPARAZIONE DATI
            BigInteger totalPriceInt = BigDecimal.valueOf(request.getTotalPrice())
                                      .multiply(BigDecimal.valueOf(100))
                                      .toBigInteger();
            
            BigInteger pointsInt = BigInteger.valueOf(request.getPoints());

            // INVIO TRANSAZIONE
            //  .send() è bloccante: 
            // aspetta che la transazione venga minata.
            TransactionReceipt receipt = contract.recordOrder(
                    request.getOrderId(),
                    request.getRiderId(),
                    request.getShopId(),
                    request.getClientId(),
                    request.getOrderDate(),
                    totalPriceInt,
                    request.getResult(),
                    pointsInt
            ).send();

            // Check se la transazione è valida (status 0x1 = successo)
            if (!receipt.isStatusOK()) {
                throw new BlockchainException("La transazione è stata revertita dalla Blockchain (Status: Failed)");
            }

            // AGGIORNAMENTO ENTITY
            converter.updateEntityWithBlockchainData(
                    orderEntity,
                    receipt.getTransactionHash(),
                    receipt.getBlockHash(),
                    receipt.getBlockNumber(),
                    contractAddress,
                    receipt.getGasUsed().toString()
            );

            orderEntity = blockchainRepository.save(orderEntity);

        } catch (Exception e) {
            e.printStackTrace();
            
            throw new BlockchainException("Errore critico durante la scrittura su Blockchain: " + e.getMessage());
        }
        
        return converter.toResponseDto(orderEntity);
    }

    /**
     * Legge i dati direttamente dalla Blockchain
     * Questo serve per validare la veridicità dei dati 
     */
    public OrderRegistrationDto getOrderFromChain(String dbOrderId) {
        try {
            DeliveryStorage contract = loadContract();

            // Chiamata alla funzione view del contratto 
            DeliveryStorage.Order resultStruct = contract.getOrder(dbOrderId).send();

            // Verifica se l'ordine esiste 
            if (resultStruct.riderId == null || resultStruct.riderId.isEmpty()) {
                throw new BlockchainException("Ordine non trovato sulla Blockchain con ID: " + dbOrderId);
            }

            // Dati Blockchain -> DTO 
            OrderRegistrationDto dto = new OrderRegistrationDto();
            dto.setOrderId(resultStruct.orderId);
            dto.setRiderId(resultStruct.riderId);
            dto.setShopId(resultStruct.shopId);
            dto.setClientId(resultStruct.clientId);
            dto.setOrderDate(resultStruct.orderDate);
            dto.setResult(resultStruct.result);
            
          
            double price = new BigDecimal(resultStruct.totalPrice)
                    .divide(BigDecimal.valueOf(100))
                    .doubleValue();
            dto.setTotalPrice(price);
            
            // Conversione Punti
            dto.setPoints(resultStruct.points.intValue());

            return dto;

        } catch (Exception e) {
            throw new BlockchainException("Impossibile leggere l'ordine dalla Blockchain: " + e.getMessage());
        }
    }

    // Helper per caricare il contratto 
    private DeliveryStorage loadContract() {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);
        
        return DeliveryStorage.load(
                contractAddress,
                web3j,
                credentials,
                new StaticGasProvider(GAS_PRICE, GAS_LIMIT)
        );
    }

    
    public static class BlockchainException extends RuntimeException {
        public BlockchainException(String message) {
            super(message);
        }
    }
}