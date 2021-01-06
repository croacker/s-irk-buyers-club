package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramShopDtoToString;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

/**
 * Чат магазины
 */
@AllArgsConstructor
public class ShopChat implements Chat {

    private final Long chatId;

    private final ShopService shopService;

    private final TelegramShopDtoToString toStringMapper;

    @Override
    public String getChatId() {
        return String.valueOf(chatId);
    }

    @Override
    public String findByName(String expression) {
        return shopService.getShops(expression)
                .stream().limit(10).map(toStringMapper).collect(Collectors.joining(LINE_DELIMITER));
    }

    @Override
    public String getDescription() {
        return "Поиск магазинов";
    }
}
