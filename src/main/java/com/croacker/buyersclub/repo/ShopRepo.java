package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ShopRepo extends ReactiveCrudRepository<Shop, Long> {

    Mono<Shop> findFirstByAddress(String address);

    Mono<Shop> findFirstByName(String name);

    Flux<Shop> findByNameContainingIgnoreCase(String expression, Pageable pageable);

    Flux<Shop> findByDeletedIsFalse(Pageable pageable);
}
