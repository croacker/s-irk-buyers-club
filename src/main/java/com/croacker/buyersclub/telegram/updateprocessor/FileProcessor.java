package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@AllArgsConstructor
public class FileProcessor implements UpdateProcessor{

    private final Message message;

    private final TelegramFileService telegramFileService;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        processFile(message);
        return createResponse();
    }

    private SendMessage createResponse(){
        var chat = getChat();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getChatId());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(getString("response.file.success"));
        return sendMessage;
    }

    /**
     * Получить и обработать файл, если он есть.
     * @param message
     */
    private void processFile(Message message) {
        telegramFileService.processFile(message);
    }

    private Chat getChat() {
        return chatPool.getChat(getChatId());
    }

    private Long getChatId(){
        return message.getChatId();
    }

    private String getLanguageCode(){
        return message.getFrom().getLanguageCode();
    }

    private String getString(String key){
        var languageCode = getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
