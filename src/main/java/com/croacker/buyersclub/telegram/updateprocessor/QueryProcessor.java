package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@AllArgsConstructor
public class QueryProcessor implements UpdateProcessor{

    private final Message message;

    private final LocaleService localeService;

    @Override
    public SendMessage process() {
        return null;
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
