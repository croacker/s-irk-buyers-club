package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.service.telegram.request.TelegramRequestType;
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
    public TelegramRequestType getMessageType(Update update) {
        var result = TelegramRequestType.QUERY;
        if (isCommand(update)) {
            result = TelegramRequestType.COMMAND;
        } else if (isFile(update)) {
            result = TelegramRequestType.FILE;
        } else if (isCallback(update)) {
            result = TelegramRequestType.CALLBACK;
        }
        return result;
    }

    /**
     * Получена команда start.
     *
     * @param update
     * @return
     */
    private boolean isCommand(Update update) {
        return getUpdateMessage(update).map(message ->
                        update.getMessage().hasText()
                                && update.getMessage().getText().startsWith("/"))
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
