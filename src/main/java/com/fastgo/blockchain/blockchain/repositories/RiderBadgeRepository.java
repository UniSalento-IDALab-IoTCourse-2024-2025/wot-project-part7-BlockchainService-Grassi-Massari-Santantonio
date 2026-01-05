package com.fastgo.blockchain.blockchain.repositories;

import com.fastgo.blockchain.blockchain.domain.RiderBadge;
import com.fastgo.blockchain.blockchain.domain.BadgeLevel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface RiderBadgeRepository extends MongoRepository<RiderBadge, String> {


    List<RiderBadge> findByRiderIdAndActiveTrue(String riderId);

    Optional<RiderBadge> findByRiderIdAndLevelAndActiveTrue(String riderId, BadgeLevel level);


}