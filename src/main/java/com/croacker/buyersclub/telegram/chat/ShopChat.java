package com.croacker.buyersclub.telegram.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Чат магазины
 */
@AllArgsConstructor
public class ShopChat implements Chat {

    private final Long chatId;

    @Override
    public String getChatId() {
        return String.valueOf(chatId);
    }

    @Override
    public String findByName(String expression) {
        return StringUtils.EMPTY;
    }

    @Override
    public String getDescription() {
        return "Поиск магазинов";
    }
}
