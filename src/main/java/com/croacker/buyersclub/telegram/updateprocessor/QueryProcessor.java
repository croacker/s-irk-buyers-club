package com.croacker.buyersclub.telegram.updateprocessor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@Slf4j
@AllArgsConstructor
public class QueryProcessor implements UpdateProcessor{

    private final Message message;

    @Override
    public SendMessage process() {
        return null;
    }
}
