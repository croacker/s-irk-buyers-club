package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * Сервис telegram-пользователей
 */
public interface TelegramUserService {

    Flux<TelegramUserDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<TelegramUserDto> findOne(Long id);

    Mono<TelegramUserDto> findByName(String name);

    Mono<TelegramUserDto> save(AddTelegramUserDto dto);

}
