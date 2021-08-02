package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.request.TelegramMessage;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import reactor.core.publisher.Mono;

// TODO переделать в сервис
@Slf4j
@AllArgsConstructor
public class FileProcessor implements MessageProcessor {

    private final TelegramMessage message;

    private final TelegramFileService telegramFileService;

    private final LocaleService localeService;

    @Override
    public Mono<SendMessage> process() {
        return processFile().map(this::createResponse);
    }

    // TODO класс выполняющий обработку не должен формировать ответ
    private SendMessage createResponse(String result){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(getString("response.file.success") + "\n" + result);
        return sendMessage;
    }

    /**
     * Получить и обработать файл, если он есть.
     */
    private Mono<String> processFile() {
        return telegramFileService.processFile(message);
    }

    private String getString(String key){
        var languageCode = message.getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
