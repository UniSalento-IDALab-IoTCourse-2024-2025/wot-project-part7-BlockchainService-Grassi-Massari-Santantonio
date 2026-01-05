package com.fastgo.blockchain.blockchain.dto.Nft;

import java.util.List;

public class ListBadgeResponseDto {

    private List<BadgeResponseDto> badges;

    public ListBadgeResponseDto() {}

    public ListBadgeResponseDto(List<BadgeResponseDto> badges) {
        this.badges = badges;
    }

    public List<BadgeResponseDto> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeResponseDto> badges) {
        this.badges = badges;
    }
}