package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.TelegramUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TelegramUserRepo extends CrudRepository<TelegramUser, Long> {

    Optional<TelegramUser> findByUserName(String name);

}
