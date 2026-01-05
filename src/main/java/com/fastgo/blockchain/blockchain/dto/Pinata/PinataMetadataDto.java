package com.fastgo.blockchain.blockchain.dto.Pinata;



public class PinataMetadataDto {
    private String name;

    public PinataMetadataDto() {}

    public PinataMetadataDto(String name) {
        this.name = name;
    }
    public String getName() { return name; }
}