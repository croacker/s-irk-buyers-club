package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import com.croacker.buyersclub.telegram.keyboard.MenuKeyboardBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@AllArgsConstructor
public class CommandProcessor implements UpdateProcessor{

    private final Message message;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        var chat = getChat();
        return startMenu();
    }

    public SendMessage startMenu() {
        var builder = new MenuKeyboardBuilder();
        builder.newButton().setText(getString("menu.start.products")).setData("product");
        builder.newButton().setText(getString("menu.start.shops")).setData("shop");
        builder.newButton().setText(getString("menu.start.organizations")).setData("organization");
        var sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId().toString());
        sendMessage.setText(getString("message.choosetype"));
        sendMessage.setReplyMarkup(builder.build());
        return sendMessage;
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
