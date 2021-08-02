package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import com.croacker.buyersclub.telegram.keyboard.ChatKeyboardBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;
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
    public ChatType getChatType() {
        return ChatType.PRODUCT;
    }

    @Override
    public String findByName(String expression) {
        return getProductsPrices(expression.trim())
                .stream().map(toStringMapper).collect(Collectors.joining(LINE_DELIMITER));
    }

    @Override
    public ReplyKeyboard findByName2(String expression) {
        var prices = getProductsPrices(expression.trim());
        var builder = new ChatKeyboardBuilder();
        prices.forEach(price -> builder.newButton()
                .setText(toStringMapper.map(price))
                .setData(price.getProductId().toString()));
        return builder.build();
    }

    @Override
    public String getDescription() {
        return "Поиск товаров";
    }

    private List<TelegramProductPriceDto> getProductsPrices(String expression){
        var pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        return productPriceService.getProductsPrices(expression.trim(), pageable);
    }

}
