package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.telegram.updateprocessor.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@Slf4j
public class TelegramMessageServiceImpl implements TelegramMessageService {

    @Override
    public MessageType getMessageType(Update update) {
        var result = MessageType.QUERY;
        if (isStart(update)) {
            result = MessageType.COMMAND;
        } else if (isFile(update)) {
            result = MessageType.FILE;
        } else if (isCallback(update)) {
            result = MessageType.CALLBACK;
        }
        return result;
    }

    @Override
    public Optional<Message> getMessage(Update update) {
        var message = Optional.ofNullable(update.getMessage());
        if (!message.isPresent()){
            message = Optional.ofNullable(update.getCallbackQuery().getMessage());
        }
        return message;
    }

    /**
     * Получена команда start.
     *
     * @param update
     * @return
     */
    private boolean isStart(Update update) {
        return getUpdateMessage(update).map(message ->
                        update.getMessage().hasText()
                                && update.getMessage().getText().equals("/start"))
                .orElse(false);
    }

    /**
     * Получен файл.
     *
     * @param update
     * @return
     */
    private boolean isFile(Update update) {
        return getDocument(update).map(Document::getFileId).isPresent();
    }

    /**
     * Получен запрос, например товара.
     *
     * @param update
     * @return
     */
    private boolean isCallback(Update update) {
        return getCallback(update).map(CallbackQuery::getMessage).isPresent();
    }

    /**
     * Получить тело сообщения.
     *
     * @param update сообщение
     * @return тело сообщения
     */
    private Optional<Message> getUpdateMessage(Update update) {
        return Optional.ofNullable(update.getMessage());
    }

    private Optional<CallbackQuery> getCallback(Update update) {
        return Optional.ofNullable(update.getCallbackQuery());
    }

    /**
     * Получить документ.
     *
     * @param update сообщение
     * @return документ
     */
    private Optional<Document> getDocument(Update update) {
        return getUpdateMessage(update).map(Message::getDocument);
    }
}
