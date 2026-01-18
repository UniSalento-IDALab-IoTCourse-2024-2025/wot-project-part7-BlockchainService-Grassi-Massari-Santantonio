package com.fastgo.blockchain.blockchain.restcontroller;

import com.fastgo.blockchain.blockchain.domain.RiderBadge;
import com.fastgo.blockchain.blockchain.domain.BadgeLevel;
import com.fastgo.blockchain.blockchain.dto.Nft.BadgeAssignRequestDto;
import com.fastgo.blockchain.blockchain.dto.Nft.BadgeResponseDto;
import com.fastgo.blockchain.blockchain.dto.Nft.ListBadgeResponseDto;
import com.fastgo.blockchain.blockchain.dto.Nft.RiderRequestDto;
import com.fastgo.blockchain.blockchain.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/nft")
@CrossOrigin(origins = "*")
public class NftController {

    @Autowired
    private BadgeService badgeService;

    @PostMapping("/assign")
    public ResponseEntity<?> manualAssignBadge(@RequestBody BadgeAssignRequestDto request) {
        try {
            // Converto stringa in Enum
            BadgeLevel badgeLevel = BadgeLevel.valueOf(request.getLevel().toUpperCase());
            
            RiderBadge badge = badgeService.assignBadgeToRider(request.getRiderId(), badgeLevel);
            
            return ResponseEntity.ok(badge);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Livello non valido. Usa: BRONZE, SILVER, GOLD");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore minting: " + e.getMessage());
        }
    }


    @PostMapping("/rider-badges")
   public ResponseEntity<ListBadgeResponseDto> getRiderBadges(@RequestBody RiderRequestDto request) {
        
        
        List<BadgeResponseDto> badges = badgeService.getBadgesForRider(request.getRiderId());

        if (badges.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        ListBadgeResponseDto responseDto = new ListBadgeResponseDto(badges);
        return ResponseEntity.ok(responseDto);
    }
}