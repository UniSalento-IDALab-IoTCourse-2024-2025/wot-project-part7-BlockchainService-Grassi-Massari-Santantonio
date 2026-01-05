package com.fastgo.blockchain.blockchain.dto.Nft;

import java.util.List;

public class NftMetadataResponseDto {
    
    private String name;
    private String description;
    private String image;
    private List<NftAttributeDto> attributes;

    // Costruttore vuoto e parametri
    public NftMetadataResponseDto() {}

    public NftMetadataResponseDto(String name, String description, String image, List<NftAttributeDto> attributes) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.attributes = attributes;
    }

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public List<NftAttributeDto> getAttributes() { return attributes; }
    public void setAttributes(List<NftAttributeDto> attributes) { this.attributes = attributes; }
}