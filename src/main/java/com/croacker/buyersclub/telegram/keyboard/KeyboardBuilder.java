package com.croacker.buyersclub.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface KeyboardBuilder {

    ChatButton newButton();

    ReplyKeyboard build();
}
