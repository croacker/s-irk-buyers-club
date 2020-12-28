package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TelegramShopDtoToString implements Mapper<ShopDto, String> {

    @Override
    public String map(ShopDto input) {
        return "[" + input.getName() + ", " + input.getAddress() + "]";
    }

}
