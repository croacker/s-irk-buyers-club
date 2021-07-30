package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.service.TelegramUserService;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import com.croacker.buyersclub.service.mapper.telegramuser.FromToAddTelegramUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

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
    public Long saveUser(User user) {
        var addUser = getAddUser(user);
        return save(addUser).getId();
    }

    @Override
    public Optional<User> getUser(Message message){
        Optional<User> result = Optional.empty();
        if(message != null){
            result = Optional.ofNullable(message.getFrom());
        }
        return result;
    }

    private AddTelegramUserDto getAddUser(User user){
        return mapper.map(user);
    }

    private TelegramUserDto save(AddTelegramUserDto dto) {
        return telegramUserService.findOne(dto.getId()).orElse(telegramUserService.save(dto));
    }
}
