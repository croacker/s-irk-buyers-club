package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Organization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends ReactiveCrudRepository<Organization, Long> {

    List<Organization> findByDeletedIsFalse(Pageable pageable);

    Optional<Organization> findByInn(String inn);

    List<Organization> findByNameContainingIgnoreCase(String expression, Pageable pageable);
}
