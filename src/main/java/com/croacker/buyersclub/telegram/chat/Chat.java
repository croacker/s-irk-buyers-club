package com.croacker.buyersclub.telegram.chat;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface Chat {

    /**
     * Response line delimiter in chat.
     */
    String LINE_DELIMITER = "\n";

    /**
     * Chat identifier.
     *
     * @return identifier
     */
    String getChatId();

    /**
     * Find entity by expression(use LIKE)
     *
     * @param expression
     * @return entities representation
     */
    String findByName(String expression);

    /**
     * Find entity by expression(use LIKE)
     *
     * @param expression
     * @return entities representation
     */
    ReplyKeyboard findByName2(String expression);

    /**
     * Chat description.
     *
     * @return description
     */
    String getDescription();
}
