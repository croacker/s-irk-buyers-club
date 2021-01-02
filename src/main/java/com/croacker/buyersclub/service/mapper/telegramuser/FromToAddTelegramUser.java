package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class FromToAddTelegramUser implements Mapper<User, AddTelegramUserDto> {
    @Override
    public AddTelegramUserDto map(User input) {
        return new AddTelegramUserDto()
                .setId(input.getId().longValue())
                .setUserName(input.getUserName())
                .setFirstName(input.getFirstName())
                .setLastName(input.getLastName());
    }
}
