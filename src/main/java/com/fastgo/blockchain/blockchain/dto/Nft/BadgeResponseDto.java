package com.fastgo.blockchain.blockchain.dto.Nft;

import java.time.Instant;

public class BadgeResponseDto {
    private String level;
    private String tokenId;
    private String publicUrl; 
    private String ipfsUri;   
    private Instant assignedAt;

    public BadgeResponseDto(String level, String tokenId, String publicUrl, String ipfsUri, Instant assignedAt) {
        this.level = level;
        this.tokenId = tokenId;
        this.publicUrl = publicUrl;
        this.ipfsUri = ipfsUri;
        this.assignedAt = assignedAt;
    }

    // Getters e Setters
    public String getLevel() { return level; }
    public String getTokenId() { return tokenId; }
    public String getPublicUrl() { return publicUrl; }
    public String getIpfsUri() { return ipfsUri; }
    public Instant getAssignedAt() { return assignedAt; }
}