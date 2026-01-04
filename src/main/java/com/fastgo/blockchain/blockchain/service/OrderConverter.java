package com.fastgo.blockchain.blockchain.service;

import com.fastgo.blockchain.blockchain.domain.BlockchainData;
import com.fastgo.blockchain.blockchain.domain.OrderBlockchain;
import com.fastgo.blockchain.blockchain.dto.BlockchainReceiptDto;
import com.fastgo.blockchain.blockchain.dto.OrderBlockchainResponseDto;
import com.fastgo.blockchain.blockchain.dto.OrderRegistrationDto;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.Instant;

@Component
public class OrderConverter {

    /**
     * Converte il DTO di registrazione in un'entità di dominio pronta per il salvataggio.
     * Inizializza BlockchainData a null o con valori di default se necessario.
     */
    public OrderBlockchain toEntity(OrderRegistrationDto dto) {
        if (dto == null) {
            return null;
        }

        OrderBlockchain entity = new OrderBlockchain();
        entity.setOrderId(dto.getOrderId());
        entity.setRiderId(dto.getRiderId());
        entity.setShopId(dto.getShopId());
        entity.setClientId(dto.getClientId());
        entity.setOrderDate(dto.getOrderDate());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setResult(dto.getResult());
        entity.setPoints(dto.getPoints());

        
        entity.setBlockchainData(null);

        return entity;
    }

    /**
     * Converte l'entità di dominio completa nel DTO di risposta per il client.
     */
    public OrderBlockchainResponseDto toResponseDto(OrderBlockchain entity) {
        if (entity == null) {
            return null;
        }

        OrderBlockchainResponseDto dto = new OrderBlockchainResponseDto();
        dto.setOrderId(entity.getOrderId());
        dto.setRiderId(entity.getRiderId());
        dto.setShopId(entity.getShopId());
        dto.setClientId(entity.getClientId());
        dto.setOrderDate(entity.getOrderDate());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setResult(entity.getResult());
        dto.setPoints(entity.getPoints());

        
        if (entity.getBlockchainData() != null) {
            dto.setBlockchainReceipt(toBlockchainReceiptDto(entity.getBlockchainData()));
        }

        return dto;
    }

    /**
     * Helper per convertire l'oggetto di dominio BlockchainData nel DTO corrispondente.
     */
    private BlockchainReceiptDto toBlockchainReceiptDto(BlockchainData data) {
        if (data == null) {
            return null;
        }

        BlockchainReceiptDto dto = new BlockchainReceiptDto();
        dto.setSynced(data.isSynced());
        dto.setSyncTimestamp(data.getSyncTimestamp());
        dto.setTransactionHash(data.getTransactionHash());
        dto.setBlockHash(data.getBlockHash());
        dto.setBlockNumber(data.getBlockNumber());
        dto.setContractAddress(data.getContractAddress());
        dto.setGasUsed(data.getGasUsed());

        return dto;
    }
    
    /**
     * Metodo utile per aggiornare l'entità esistente con i nuovi dati dalla Blockchain
     * dopo che lo smart contract è stato chiamato.
     */
    public void updateEntityWithBlockchainData(OrderBlockchain entity, 
                                               String txHash, 
                                               String blockHash, 
                                               Long blockNumber, 
                                               String contractAddress, 
                                               String gasUsed) {
        BlockchainData bcData = new BlockchainData();
        bcData.setSynced(true);
        bcData.setSyncTimestamp(Instant.now());
        bcData.setTransactionHash(txHash);
        bcData.setBlockHash(blockHash);
        bcData.setBlockNumber(blockNumber);
        bcData.setContractAddress(contractAddress);
        bcData.setGasUsed(gasUsed);
        
        entity.setBlockchainData(bcData);
    }


    public void updateEntityWithBlockchainData(OrderBlockchain entity, 
                                               String txHash, 
                                               String blockHash, 
                                               BigInteger blockNumber, 
                                               String contractAddress, 
                                               String gasUsed) {
        BlockchainData bcData = new BlockchainData();
        bcData.setSynced(true);
        bcData.setSyncTimestamp(Instant.now());
        bcData.setTransactionHash(txHash);
        bcData.setBlockHash(blockHash);
        
        // Conversione sicura da BigInteger a Long
        bcData.setBlockNumber(blockNumber.longValue()); 
        
        bcData.setContractAddress(contractAddress);
        bcData.setGasUsed(gasUsed);
        
        entity.setBlockchainData(bcData);
    }

}