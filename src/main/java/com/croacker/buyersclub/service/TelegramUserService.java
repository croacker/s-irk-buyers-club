package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * Сервис telegram-пользователей
 */
public interface TelegramUserService {

    List<TelegramUserDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Optional<TelegramUserDto> findOne(Long id);

    Optional<TelegramUserDto> findByName(String name);

    TelegramUserDto save(AddTelegramUserDto dto);

}
