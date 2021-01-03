package com.croacker.buyersclub.service.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface TelegramTelegramUserService {

    Long saveUser(Message message);

}
