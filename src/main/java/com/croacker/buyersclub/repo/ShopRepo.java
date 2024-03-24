package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepo extends ReactiveCrudRepository<Shop, Long> {

    Optional<Shop> findFirstByAddress(String address);

    Optional<Shop> findFirstByName(String name);

    List<Shop> findByNameContainingIgnoreCase(String expression, Pageable pageable);

    List<Shop> findByDeletedIsFalse(Pageable pageable);
}
