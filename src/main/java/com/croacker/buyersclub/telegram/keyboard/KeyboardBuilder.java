package com.croacker.buyersclub.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface KeyboardBuilder {

    ChatButton newButton();

    ChatButton newButton(String text);

    ReplyKeyboard build();
}
