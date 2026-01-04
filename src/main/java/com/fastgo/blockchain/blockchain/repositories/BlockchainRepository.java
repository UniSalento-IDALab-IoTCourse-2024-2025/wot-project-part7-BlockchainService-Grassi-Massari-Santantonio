package com.fastgo.blockchain.blockchain.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fastgo.blockchain.blockchain.domain.OrderBlockchain;

public interface BlockchainRepository extends MongoRepository<OrderBlockchain, String> {

    Optional<OrderBlockchain> findByOrderId(String orderId);
    
    
}
