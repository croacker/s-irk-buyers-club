package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.domain.TelegramUser;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToTelegramUser implements Mapper<AddTelegramUserDto, TelegramUser> {
    @Override
    public TelegramUser map(AddTelegramUserDto input) {
        return new TelegramUser()
                .setId(input.getId())
                .setIsBot(input.getIsBot())
                .setUserName(input.getUserName())
                .setFirstName(input.getFirstName())
                .setLastName(input.getLastName())
                .setCanJoinGroups(input.getCanJoinGroups())
                .setCanReadAllGroupMessages(input.getCanReadAllGroupMessages())
                .setSupportInlineQueries(input.getSupportInlineQueries());
    }
}
