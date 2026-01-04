package com.fastgo.blockchain.blockchain.dto;

import java.time.Instant;

public class BlockchainReceiptDto {
    private boolean synced;
    private Instant syncTimestamp;
    
    private String transactionHash;
    private String blockHash;
    private Long blockNumber;
    private String contractAddress;
    private String gasUsed;

    // Getters and Setters
    public boolean isSynced() {
        return synced;
    }
    public void setSynced(boolean synced) {
        this.synced = synced;}
    public Instant getSyncTimestamp() {
        return syncTimestamp;}
    public void setSyncTimestamp(Instant syncTimestamp) {
        this.syncTimestamp = syncTimestamp;}
    public String getTransactionHash() {
        return transactionHash;}
    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;}
    public String getBlockHash() {
        return blockHash;}
    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;}
    public Long getBlockNumber() {
        return blockNumber;}
    public void setBlockNumber(Long blockNumber) {
        this.blockNumber = blockNumber;}
    public String getContractAddress() {
        return contractAddress;}
    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;}
    public String getGasUsed() {
        return gasUsed;}
    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;}
}
