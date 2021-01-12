package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@AllArgsConstructor
public class FileProcessor implements UpdateProcessor{

    private final Message message;

    private final TelegramFileService telegramFileService;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        processFile(message);
        return createResponse();
    }

    private SendMessage createResponse(){
        var chatId = getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(getString("response.file.inprocess"));
        return sendMessage;
    }

    /**
     * Получить и обработать файл, если он есть.
     * @param message
     */
    private void processFile(Message message) {
        telegramFileService.processFile(message);
    }

    private String getChatId(){
        return message.getChatId().toString();
    }

    private String getLanguageCode(){
        return message.getFrom().getLanguageCode();
    }

    private String getString(String key){
        var languageCode = getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
