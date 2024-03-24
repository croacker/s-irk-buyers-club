package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.AppUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AppUserRepo extends ReactiveCrudRepository<AppUser, Long> {

    Mono<AppUser> findOneWithAuthoritiesByUsername(String username);

}
