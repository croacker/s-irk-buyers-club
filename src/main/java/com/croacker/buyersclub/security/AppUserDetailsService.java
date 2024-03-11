package com.croacker.buyersclub.security;

import com.croacker.buyersclub.domain.AppUser;
import com.croacker.buyersclub.repo.AppUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static com.croacker.buyersclub.security.AuthoritiesConstants.ADMIN;

@Component("userDetailsService")
@RequiredArgsConstructor
@Slf4j
public class AppUserDetailsService implements ReactiveUserDetailsService {

    private final AppUserRepo appUserRepo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.debug("authenticating {}", username);
        return appUserRepo
                .findOneWithAuthoritiesByUsername(username)
                .switchIfEmpty(Mono.error(
                        new UsernameNotFoundException("user: {} " + username + " was not found in the database"))
                )
                .map(user -> createSpringSecurityUser(user));
    }

    // TODO to mapper class
    private User createSpringSecurityUser(AppUser user) {
        return new User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(ADMIN))
        );
    }
}
