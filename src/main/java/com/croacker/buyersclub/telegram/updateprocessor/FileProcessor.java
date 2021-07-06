package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class FileProcessor implements UpdateProcessor{

    private final Message message;

    private final TelegramFileService telegramFileService;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public Mono<SendMessage> process() {
        return processFile(message).map(this::createResponse);
    }

    private SendMessage createResponse(String result){
        var chat = getChat();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getChatId());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(getString("response.file.success") + "\n" + result);
        return sendMessage;
    }

    /**
     * Получить и обработать файл, если он есть.
     * @param message
     */
    private Mono<String> processFile(Message message) {
        return telegramFileService.processFile(message);
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
