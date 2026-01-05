package com.fastgo.blockchain.blockchain.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "rider_badges")
public class RiderBadge {

    @Id
    private String id;

    @Indexed 
    private String riderId;

    private BadgeLevel level;

    // Dati NFT
    private String tokenId;       
    private String transactionHash; 
    private String contractAddress; 
    
    private boolean active;       // Se false, il badge è stato revocato 
    
    private Instant assignedAt;
    private Instant revokedAt;

    private String tokenUri;

    
    public RiderBadge() {}



    public RiderBadge(String riderId, BadgeLevel level, String tokenId, String txHash, String contractAddress, String tokenUri) {
        this.riderId = riderId;
        this.level = level;
        this.tokenId = tokenId;
        this.transactionHash = txHash;
        this.contractAddress = contractAddress;
        this.tokenUri = tokenUri;
        this.active = true;
        this.assignedAt = Instant.now();
        
    }

    // --- GETTERS & SETTERS ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRiderId() { return riderId; }
    public void setRiderId(String riderId) { this.riderId = riderId; }

    public BadgeLevel getLevel() { return level; }
    public void setLevel(BadgeLevel level) { this.level = level; }

    public String getTokenId() { return tokenId; }
    public void setTokenId(String tokenId) { this.tokenId = tokenId; }

    public String getTransactionHash() { return transactionHash; }
    public void setTransactionHash(String transactionHash) { this.transactionHash = transactionHash; }

    public String getContractAddress() { return contractAddress; }
    public void setContractAddress(String contractAddress) { this.contractAddress = contractAddress; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Instant getAssignedAt() { return assignedAt; }
    public void setAssignedAt(Instant assignedAt) { this.assignedAt = assignedAt; }

    public Instant getRevokedAt() { return revokedAt; }
    public void setRevokedAt(Instant revokedAt) { this.revokedAt = revokedAt; }

    public String getTokenUri() { return tokenUri; }
    public void setTokenUri(String tokenUri) { this.tokenUri = tokenUri; }
}