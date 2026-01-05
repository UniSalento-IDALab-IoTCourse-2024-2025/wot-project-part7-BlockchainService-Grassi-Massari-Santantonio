package com.fastgo.blockchain.blockchain.dto.Nft;

public class RiderPointsRequestDto {
    private String riderId;

    public RiderPointsRequestDto() {}

    public RiderPointsRequestDto(String riderId) {
        this.riderId = riderId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }
}