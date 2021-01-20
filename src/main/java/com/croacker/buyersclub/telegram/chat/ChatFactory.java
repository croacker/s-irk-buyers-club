package com.croacker.buyersclub.telegram.chat;

/**
 * Фабрика чатов.
 */
public interface ChatFactory {

    /**
     * Создать чат.
     * @param id идентификатор
     * @param type тип
     * @return чат
     */
    Chat createChat(Long id, ChatType type);

}
