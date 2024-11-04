package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Cashier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * Кассиры.
 */
public interface CashierRepo extends ReactiveCrudRepository<Cashier, Long> {

    Flux<Cashier> findByDeletedIsFalse(Pageable pageable);

    Mono<Cashier> findByNameAndShopId(String name, Long shopId);

}
