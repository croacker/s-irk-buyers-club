package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@AllArgsConstructor
public class CallbackProcessor implements UpdateProcessor{

    private final CallbackQuery callbackQuery;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        return createResponse(); // TODO вернуть детали информации о товаре
    }

    private SendMessage createResponse(){
        var chat = getChat();
        var text = getString("chat." + chat.getChatType().toString().toLowerCase() + ".welcome");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId().toString());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(text);
        return sendMessage;
    }

    private Chat getChat() {
        return chatPool.getChat(getChatId());
    }

    private Message getMessage(){
        return callbackQuery.getMessage();
    }

    private Long getChatId(){
        return getMessage().getChatId();
    }

    private String getLanguageCode(){
        return getMessage().getFrom().getLanguageCode();
    }

    private String getString(String key){
        var languageCode = getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
