package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.domain.TelegramUser;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TelegramUserToDtoMapper implements Mapper<TelegramUser, TelegramUserDto> {

    @Override
    public TelegramUserDto map(TelegramUser input) {
        return new TelegramUserDto()
                .setId(input.getId())
                .setUserName(input.getUserName())
                .setFirstName(input.getFirstName())
                .setLastName(input.getLastName());
    }

}
