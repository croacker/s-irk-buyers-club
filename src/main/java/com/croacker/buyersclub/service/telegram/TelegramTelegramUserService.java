package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

public interface TelegramTelegramUserService {

    Optional<AddTelegramUserDto> telegramUser(Message message);

}
