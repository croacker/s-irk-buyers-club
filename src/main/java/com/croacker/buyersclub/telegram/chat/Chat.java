package com.croacker.buyersclub.telegram.chat;

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
     * Chat description.
     *
     * @return description
     */
    String getDescription();
}
