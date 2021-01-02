package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
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

    @Override
    public Optional<AddTelegramUserDto> telegramUser(Message message) {
        Optional<AddTelegramUserDto> result = Optional.empty();
        if(message != null){
            result = Optional.ofNullable(message.getFrom()).map(mapper);
        }
        return result;
    }
}
