package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.mapper.Mapper;
import com.croacker.buyersclub.service.mapper.cashier.CashierToDtoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Кассиры.
 */
public interface CashierRepo extends CrudRepository<Cashier, Long> {

    List<Cashier> findByDeletedIsFalse(Pageable pageable);

    Optional<Cashier> findByName(String name);

}
