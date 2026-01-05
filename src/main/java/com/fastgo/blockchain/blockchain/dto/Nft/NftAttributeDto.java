package com.fastgo.blockchain.blockchain.dto.Nft;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NftAttributeDto {
    
    @JsonProperty("trait_type") // Fondamentale per OpenSea
    private String traitType;
    
    private String value;

    public NftAttributeDto(String traitType, String value) {
        this.traitType = traitType;
        this.value = value;
    }

    // Getters
    public String getTraitType() { return traitType; }
    public String getValue() { return value; }
}