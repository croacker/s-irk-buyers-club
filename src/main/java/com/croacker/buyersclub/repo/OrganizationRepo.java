package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends ReactiveCrudRepository<Organization, Long> {

    Flux<Organization> findByDeletedIsFalse(Pageable pageable);

    Mono<Organization> findByInn(String inn);

    Flux<Organization> findByNameContainingIgnoreCase(String expression, Pageable pageable);
}
