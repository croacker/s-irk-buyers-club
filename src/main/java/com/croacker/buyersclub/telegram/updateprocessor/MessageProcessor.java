package com.croacker.buyersclub.telegram.updateprocessor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import reactor.core.publisher.Mono;

public interface MessageProcessor {

    Mono<SendMessage> process();

}
