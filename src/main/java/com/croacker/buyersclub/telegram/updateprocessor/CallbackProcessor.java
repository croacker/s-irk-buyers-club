package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.request.TelegramMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class CallbackProcessor implements MessageProcessor {

    private final TelegramMessage message;

    private final LocaleService localeService;

    @Override
    public Mono<SendMessage> process() {
        return createResponse();
    }

    private Mono<SendMessage> createResponse(){
        var text = getString("chat...welcome");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(text);
        return Mono.just(sendMessage);
    }

    private String getString(String key){
        var languageCode = message.getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
