package com.croacker.buyersclub.telegram.chat;

/**
 * Фабрика чатов.
 */
@Deprecated
public interface ChatFactory {

    /**
     * Создать чат.
     * @param id идентификатор
     * @param type тип
     * @return чат
     */
    Chat createChat(String id, ChatType type);

}
