package com.croacker.buyersclub.service.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

/**
 * Сервис работы с файлами telegram.
 */
public interface TelegramFileService {

    /**
     * Обработать файл, при наличии.
     *
     * @param message сообщение
     */
    void processFile(Message message);

    /**
     * Получить идентификатор файла.
     *
     * @param message сообщение
     * @return идентификатор файла
     */
    Optional<String> getFileId(Message message);
}
