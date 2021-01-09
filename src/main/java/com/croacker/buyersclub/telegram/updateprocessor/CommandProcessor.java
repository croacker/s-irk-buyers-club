package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.telegram.keyboard.MenuKeyboardBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@Slf4j
@AllArgsConstructor
public class CommandProcessor implements UpdateProcessor{

    private final Message message;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        return startMenu();
    }

    public SendMessage startMenu() {
        var builder = new MenuKeyboardBuilder();
        builder.newButton().setText(getString("menu.start.products")).setData("product");
        builder.newButton().setText(getString("menu.start.shops")).setData("shop");
        builder.newButton().setText(getString("menu.start.Organizations")).setData("organization");
        var sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId());
        sendMessage.setText(getString("message.choosetype"));
        sendMessage.setReplyMarkup(builder.build());
        return sendMessage;
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
