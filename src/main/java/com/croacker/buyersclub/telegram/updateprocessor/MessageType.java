package com.croacker.buyersclub.telegram.updateprocessor;

/**
 * Тип сообщения.
 */
public enum MessageType {
    /**
     * Файл для загрузки
     */
    FILE,
    /**
     * Запрос
     */
    QUERY,
    /**
     * Обратный вызов
     */
    CALLBACK,
    /**
     * Комманда
     */
    COMMAND,
    /**
     * Неизвестно
     */
    UNDEFINED

}
