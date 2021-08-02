package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.request.TelegramMessage;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class QueryProcessor implements MessageProcessor {

    private final TelegramMessage message;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public Mono<SendMessage> process() {
        var chat = getChat();
        return createResponse(chat.findByName2(getExpression()));
    }

    private Mono<SendMessage> createResponse(ReplyKeyboard keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(getText(keyboard));
        sendMessage.setReplyMarkup(keyboard);
        return Mono.just(sendMessage);
    }

    private String getText(ReplyKeyboard keyboard){
        String result;
        if(((InlineKeyboardMarkup) keyboard).getKeyboard().isEmpty()){
            result = getString("response.search.empty");
        }else {
            result = getString("response.search.caption");
        }
        return result;
    }

    private String getExpression() {
        return message.getText();
    }

    private Chat getChat() {
        return chatPool.getChat(message.getChatId());
    }

    private String getString(String key){
        var languageCode = message.getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
