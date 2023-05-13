package com.croacker.buyersclub.telegram.chat;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Deprecated
public interface Chat {

    /**
     * Разделитель строк.
     */
    String LINE_DELIMITER = "\n";

    /**
     * Идентификатор чата.
     *
     * @return identifier
     */
    String getChatId();

    /**
     * Найти сущности по наименованию(используя LIKE)
     *
     * @param expression выражение - часть наименования
     * @return представление сущностей в виде telegram-клавиатуры
     */
    ReplyKeyboard findByName(String expression);

    /**
     * Заголовок чата.
     *
     * @return description
     */
    String getDescription();
}
