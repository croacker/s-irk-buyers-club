package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Slf4j
@AllArgsConstructor
public class QueryProcessor implements UpdateProcessor{

    private final Message message;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        var chat = getChat();
        return createResponse(chat.findByName2(getExpression()));
    }

    private SendMessage createResponse(ReplyKeyboard keyboard) {
        var chatId = getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(getString("response.search.caption"));
        sendMessage.setReplyMarkup(keyboard);
        return sendMessage;
    }

    private String getExpression() {
        return message.getText();
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
