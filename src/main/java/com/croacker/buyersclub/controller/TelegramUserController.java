package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.TelegramUserService;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/telegram-user")
@AllArgsConstructor
@Slf4j
public class TelegramUserController implements TelegramUserOperations{

    private final TelegramUserService service;

    @Override
    public Flux<TelegramUserDto> getAllTelegramUsers(int page, int size, String sort, Sort.Direction direction) {
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<TelegramUserDto> getTelegramUser(Long id) {
        return service.findOne(id).map(Mono::just).orElse(Mono.empty());
    }

}
