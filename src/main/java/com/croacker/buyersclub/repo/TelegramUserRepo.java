package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.TelegramUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface TelegramUserRepo extends ReactiveCrudRepository<TelegramUser, Long> {

    Mono<TelegramUser> findByUserName(String name);

}
