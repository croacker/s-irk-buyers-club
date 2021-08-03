package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.telegram.TelegramShopDtoToString;
import com.croacker.buyersclub.telegram.keyboard.ChatKeyboardBuilder;
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

    private final String chatId;

    private final ShopService shopService;

    private final TelegramShopDtoToString toStringMapper;

    @Override
    public String getChatId() {
        return String.valueOf(chatId);
    }

    @Override
    public ReplyKeyboard findByName(String expression) {
        var shops = getShops(expression);
        var builder = new ChatKeyboardBuilder();
        shops.forEach(shop -> builder.newButton()
                .setText(toStringMapper.map(shop))
                .setData(shop.getId().toString()));
        return builder.build();
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
