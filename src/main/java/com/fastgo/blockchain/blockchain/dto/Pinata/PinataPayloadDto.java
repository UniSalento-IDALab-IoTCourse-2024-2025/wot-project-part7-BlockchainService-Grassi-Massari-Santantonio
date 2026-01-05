package com.fastgo.blockchain.blockchain.dto.Pinata;

import com.fastgo.blockchain.blockchain.dto.Nft.NftMetadataResponseDto;

public class PinataPayloadDto {
   
    private NftMetadataResponseDto pinataContent;
    private PinataMetadataDto pinataMetadata;

public PinataPayloadDto(NftMetadataResponseDto content, String name) {
        this.pinataContent = content;
        this.pinataMetadata = new PinataMetadataDto(name);
    }

    // Getters
    public NftMetadataResponseDto getPinataContent() { return pinataContent; }
    public void setPinataContent(NftMetadataResponseDto pinataContent) { this.pinataContent = pinataContent; } // Setter utile per Jackson

    public PinataMetadataDto getPinataMetadata() { return pinataMetadata; }
    public void setPinataMetadata(PinataMetadataDto pinataMetadata) { this.pinataMetadata = pinataMetadata; } // Setter utile per Jackson
}