package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

/**
 * Чат товары
 */
@AllArgsConstructor
public class ProductChat implements Chat{

    private final Long chatId;

    private final ProductPriceService productPriceService;

    private final TelegramProductPriceDtoToString toStringMapper;

    @Override
    public String getChatId() {
        return String.valueOf(chatId);
    }

    @Override
    public String findByName(String expression) {
        return productPriceService.getProductsPrices(expression.trim())
                .stream().limit(10).map(toStringMapper).collect(Collectors.joining(LINE_DELIMITER));
    }

    @Override
    public String getDescription() {
        return "Поиск товаров";
    }

}
