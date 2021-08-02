package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.telegram.updateprocessor.MessageType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

public interface TelegramMessageService {

    /**
     * Тип сообщения.
     * @param update telegram-сообщение
     * @return тип сообщения
     */
    MessageType getMessageType(Update update);

    /**
     * Получить тело сообщения
     * @param update сообщение
     * @return тело сообщения
     */
    Optional<Message> getMessage(Update update);

    /**
     * Получить тело сообщения
     * @param update сообщение
     * @return тело сообщения
     */
    Optional<User> getFrom(Update update);

}
