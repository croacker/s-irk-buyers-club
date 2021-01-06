package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepo extends CrudRepository<Shop, Long> {

    Optional<Shop> findByAddress(String address);

    Optional<Shop> findByName(String name);

    List<Shop> findByNameContainingIgnoreCase(String expression);

    List<Shop> findByDeletedIsFalse(Pageable pageable);
}
