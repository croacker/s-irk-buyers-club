package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.telegram.updateprocessor.MessageType;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramMessageService {

    /**
     * Тип сообщения.
     * @param update telegram-сообщение
     * @return тип сообщения
     */
    MessageType getMessageType(Update update);

}
