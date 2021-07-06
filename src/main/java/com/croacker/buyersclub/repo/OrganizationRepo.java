package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends CrudRepository<Organization, Long> {

    List<Organization> findByDeletedIsFalse(Pageable pageable);

    Optional<Organization> findByInn(String inn);

    List<Organization> findByNameContainingIgnoreCase(String expression, Pageable pageable);
}
