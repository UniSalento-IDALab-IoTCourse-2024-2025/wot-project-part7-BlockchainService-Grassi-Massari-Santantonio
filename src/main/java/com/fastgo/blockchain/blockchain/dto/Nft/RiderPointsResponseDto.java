package com.fastgo.blockchain.blockchain.dto.Nft;

public class RiderPointsResponseDto {
    private String riderId;
    private int totalPoints;

    public RiderPointsResponseDto(String riderId, int totalPoints) {
        this.riderId = riderId;
        this.totalPoints = totalPoints;
    }

    // Getters e Setters
    public String getRiderId() { return riderId; }
    public void setRiderId(String riderId) { this.riderId = riderId; }
    public int getTotalPoints() { return totalPoints; }
    public void setTotalPoints(int totalPoints) { this.totalPoints = totalPoints; }
}