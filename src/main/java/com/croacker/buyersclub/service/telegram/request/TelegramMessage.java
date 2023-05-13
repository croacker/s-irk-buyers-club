package com.croacker.buyersclub.service.telegram.request;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Обертка для telegram-сообщения.
 */
public interface TelegramMessage {

    TelegramRequestType getTelegramRequestType();

    Message getMessage();

    User getFrom();

    String getLanguageCode();

    String getChatId();

    String getText();

}
