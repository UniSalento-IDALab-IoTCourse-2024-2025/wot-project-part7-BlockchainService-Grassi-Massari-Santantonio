package com.fastgo.blockchain.blockchain.dto;

public class OrderBlockchainResponseDto {

    private String orderId;
    private String riderId;
    private String shopId;
    private String clientId;
    private String orderDate;
    private double totalPrice;
    private String result;
    private int points;

    private BlockchainReceiptDto blockchainReceipt;

    // Getters and Setters
    public String getOrderId() {
        return orderId;}
    public void setOrderId(String orderId) {
        this.orderId = orderId;}
    public String getRiderId() {
        return riderId;}
    public void setRiderId(String riderId) {
        this.riderId = riderId;}
    public String getShopId() {
        return shopId;}
    public void setShopId(String shopId) {
        this.shopId = shopId;}
    public String getClientId() {
        return clientId;}
    public void setClientId(String clientId) {
        this.clientId = clientId;}
    public String getOrderDate() {
        return orderDate;}
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;}
    public double getTotalPrice() {
        return totalPrice;}
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;}
    public String getResult() {
        return result;}
    public void setResult(String result) {
        this.result = result;}
    public int getPoints() {
        return points;}
    public void setPoints(int points) {
        this.points = points;}
    public BlockchainReceiptDto getBlockchainReceipt() {
        return blockchainReceipt;}
    public void setBlockchainReceipt(BlockchainReceiptDto blockchainReceipt) {
        this.blockchainReceipt = blockchainReceipt;}
    
}
