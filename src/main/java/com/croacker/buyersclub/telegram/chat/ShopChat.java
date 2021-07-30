package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.telegram.TelegramShopDtoToString;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;
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
    public ChatType getChatType() {
        return ChatType.SHOP;
    }

    @Override
    public String findByName(String expression) {
        return getShops(expression)
                .stream().map(toStringMapper).collect(Collectors.joining(LINE_DELIMITER));
    }

    @Override
    public ReplyKeyboard findByName2(String expression) {
        return null;
    }

    @Override
    public String getDescription() {
        return "Поиск магазинов";
    }

    private List<ShopDto> getShops(String expression){
        var pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        return shopService.getShops(expression, pageable);
    }
}
