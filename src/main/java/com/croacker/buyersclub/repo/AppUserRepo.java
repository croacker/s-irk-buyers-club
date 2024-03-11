package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.AppUser;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {

    Mono<AppUser> findOneWithAuthoritiesByUsername(String username);

}
