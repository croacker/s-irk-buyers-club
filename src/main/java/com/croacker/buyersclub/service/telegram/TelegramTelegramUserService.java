package com.croacker.buyersclub.service.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

public interface TelegramTelegramUserService {

    Long saveUser(User message);

    Optional<User> getUser(Message message);

}
