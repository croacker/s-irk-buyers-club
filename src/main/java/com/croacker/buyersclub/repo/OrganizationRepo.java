package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Organization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface OrganizationRepo extends CrudRepository<Organization, Long> {

    List<Organization> findByDeletedIsFalse(Pageable pageable);

}
