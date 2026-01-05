package com.fastgo.blockchain.blockchain.dto.Nft;

public class RiderRequestDto {
    private String riderId;


    public RiderRequestDto() {}

    public RiderRequestDto(String riderId) {
        this.riderId = riderId;
    }

    public String getRiderId() { return riderId; }
    public void setRiderId(String riderId) { this.riderId = riderId; }
}