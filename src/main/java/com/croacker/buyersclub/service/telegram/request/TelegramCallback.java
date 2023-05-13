package com.croacker.buyersclub.service.telegram.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
@AllArgsConstructor
public class TelegramCallback implements TelegramMessage{

    private final TelegramRequestType telegramRequestType;

    private final Update update;

    @Override
    public Message getMessage() {
        return update.getCallbackQuery().getMessage();
    }

    @Override
    public User getFrom() {
        return update.getCallbackQuery().getFrom();
    }

    @Override
    public String getLanguageCode() {
        return getFrom().getLanguageCode();
    }

    @Override
    public String getChatId() {
        return String.valueOf(getMessage().getChatId());
    }

    @Override
    public String getText() {
        return getMessage().getText();
    }

}
