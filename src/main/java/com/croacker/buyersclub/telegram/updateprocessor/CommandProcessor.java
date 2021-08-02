package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import com.croacker.buyersclub.telegram.keyboard.InlineKeyboardBuilder;
import com.croacker.buyersclub.telegram.keyboard.ReplyKeyboardBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class CommandProcessor implements UpdateProcessor{

    private final Message message;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public Mono<SendMessage> process() {
        var chat = getChat();
        return startMenu();
    }

    public Mono<SendMessage> startMenu() {
        var builder = new ReplyKeyboardBuilder();
        builder.newButton(getString("menu.start.data"));
        builder.newButton(getString("menu.start.reports"));
        var sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId().toString());
        sendMessage.setText(getString("message.choosetype"));
        sendMessage.setReplyMarkup(builder.build());
        return Mono.just(sendMessage);
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
