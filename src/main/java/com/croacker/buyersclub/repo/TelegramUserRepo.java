package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.TelegramUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.Optional;

public interface TelegramUserRepo extends ReactiveCrudRepository<TelegramUser, Long> {

    Optional<TelegramUser> findByUserName(String name);

}
