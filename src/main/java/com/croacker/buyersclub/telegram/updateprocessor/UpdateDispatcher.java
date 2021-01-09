package com.croacker.buyersclub.telegram.updateprocessor;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateDispatcher {

    UpdateProcessor getProcessor(Update update);

}
