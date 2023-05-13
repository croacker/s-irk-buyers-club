package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.telegram.request.TelegramMessage;

public interface MessageDispatcher {

    MessageProcessor getProcessor(TelegramMessage update);

}
