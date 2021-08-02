package com.croacker.buyersclub.telegram.keyboard;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardBuilder implements KeyboardBuilder {

    @Getter
    private List<ChatButton> buttons = new ArrayList<>();

    public ChatButton newButton(){
        var button = new ChatButton();
        buttons.add(button);
        return button;
    }

    @Override
    public ChatButton newButton(String text) {
        return newButton().setText(text);
    }

    @Override
    public ReplyKeyboard build() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        List<KeyboardRow> rowList = new ArrayList<>();
        var row = new KeyboardRow();
        buttons.forEach(define -> {
            row.add(define.getText());
        });
        rowList.add(row);
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

}
