package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@AllArgsConstructor
public class CallbackProcessor implements UpdateProcessor{

    private final CallbackQuery callbackQuery;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        var chat = getChat();
        return null;
    }

    private Chat getChat() {
        return chatPool.getChat(callbackQuery.getMessage().getChatId(), callbackQuery.getData());
    }

    private Chat createChat(Update update) {
//        var chatId = update.getCallbackQuery().getMessage().getChatId();
//        var type = update.getCallbackQuery().getData();
//        var chat = chatFactory.createChat(chatId, type);
//        chatPool.put(chatId, chat);
        return null;
    }

    private Message getMessage(){
        return callbackQuery.getMessage();
    }

    private String getChatId(){
        return getMessage().getChatId().toString();
    }

    private String getLanguageCode(){
        return getMessage().getFrom().getLanguageCode();
    }

    private String getString(String key){
        var languageCode = getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
