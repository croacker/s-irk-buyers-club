package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.service.TelegramUserService;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import com.croacker.buyersclub.service.mapper.telegramuser.FromToAddTelegramUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

/**
 * Сервис работы с telegram user.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TelegramTelegramUserServiceImpl implements TelegramTelegramUserService{

    private final FromToAddTelegramUser mapper;

    private final TelegramUserService telegramUserService;

    @Override
    public Long saveUser(Message message) {
        return getUser(message).map(this::save).map(TelegramUserDto::getId).orElse(-1L);
    }

    private TelegramUserDto save(AddTelegramUserDto dto) {
        return telegramUserService.findOne(dto.getId()).orElse(telegramUserService.save(dto));
    }

    private Optional<AddTelegramUserDto> getUser(Message message){
        Optional<AddTelegramUserDto> result = Optional.empty();
        if(message != null){
            result = Optional.ofNullable(message.getFrom()).map(mapper);
        }
        return result;
    }
}
