package com.croacker.buyersclub.service.telegram.request;

/**
 * Тип сообщения.
 */
public enum TelegramRequestType {
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
