package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Cashier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Кассиры.
 */
public interface CashierRepo extends ReactiveCrudRepository<Cashier, Long> {

    List<Cashier> findByDeletedIsFalse(Pageable pageable);

    Optional<Cashier> findByNameAndShopId(String name, Long shopId);

}
