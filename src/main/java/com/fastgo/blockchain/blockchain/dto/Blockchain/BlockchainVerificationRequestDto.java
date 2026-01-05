package com.fastgo.blockchain.blockchain.dto.Blockchain;

public class BlockchainVerificationRequestDto {

    private String orderId;
    private String transactionHash;
    
    // Getters and Setters
    public String getOrderId() {
        return orderId;}
    public void setOrderId(String orderId) {
        this.orderId = orderId;}
    public String getTransactionHash() {
        return transactionHash;}
    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;}
}
