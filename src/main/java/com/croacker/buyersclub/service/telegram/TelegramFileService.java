package com.croacker.buyersclub.service.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;

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

}
