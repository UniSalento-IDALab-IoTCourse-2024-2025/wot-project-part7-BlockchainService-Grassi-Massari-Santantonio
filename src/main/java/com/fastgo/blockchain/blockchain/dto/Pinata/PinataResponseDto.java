package com.fastgo.blockchain.blockchain.dto.Pinata;



import com.fasterxml.jackson.annotation.JsonProperty;

public class PinataResponseDto {
    @JsonProperty("IpfsHash") // Pinata risponde con questo campo esatto
    private String ipfsHash;

    public String getIpfsHash() { return ipfsHash; }
    public void setIpfsHash(String ipfsHash) { this.ipfsHash = ipfsHash; }
}