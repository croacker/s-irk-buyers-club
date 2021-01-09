package com.croacker.buyersclub.telegram.updateprocessor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface UpdateProcessor {

    SendMessage process();

}
