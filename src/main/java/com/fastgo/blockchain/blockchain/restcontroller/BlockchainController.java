package com.fastgo.blockchain.blockchain.restcontroller;

import com.fastgo.blockchain.blockchain.dto.Blockchain.OrderBlockchainResponseDto;
import com.fastgo.blockchain.blockchain.dto.Blockchain.OrderRegistrationDto;
import com.fastgo.blockchain.blockchain.dto.Nft.RiderPointsRequestDto;
import com.fastgo.blockchain.blockchain.dto.Nft.RiderPointsResponseDto;
import com.fastgo.blockchain.blockchain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    @Autowired
    private BlockchainService blockchainService;

   
    @PostMapping("/sync")
    public ResponseEntity<?> syncOrder(@RequestBody OrderRegistrationDto request) {
        try {
        
            OrderBlockchainResponseDto response = blockchainService.registerOrder(request);
            return ResponseEntity.ok(response);
        } catch (BlockchainService.BlockchainException e) {
   
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Blockchain Error: " + e.getMessage());
        } catch (Exception e) {
           
            return ResponseEntity.internalServerError().body("Server Error: " + e.getMessage());
        }
    }

   
    @GetMapping("/verify/{orderId}")
    public ResponseEntity<?> verifyOrder(@PathVariable String orderId) {
        try {
            OrderRegistrationDto blockchainData = blockchainService.getOrderFromChain(orderId);
            return ResponseEntity.ok(blockchainData);
        } catch (BlockchainService.BlockchainException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found on chain: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error verifying order: " + e.getMessage());
        }
    }


    @PostMapping("/rider/points")
    public ResponseEntity<RiderPointsResponseDto> getRiderPoints(@RequestBody RiderPointsRequestDto request) {
        
        if (request.getRiderId() == null || request.getRiderId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        int points = blockchainService.getRiderTotalPoints(request.getRiderId());

        RiderPointsResponseDto response = new RiderPointsResponseDto(
            request.getRiderId(), 
            points
        );

        return ResponseEntity.ok(response);
    }
}