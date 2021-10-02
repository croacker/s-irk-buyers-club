package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TelegramProductPriceDtoToString implements Mapper<TelegramProductPriceDto, String> {

    @Override
    public String map(TelegramProductPriceDto input) {
        return input.getPrice() + " руб. - " + input.getProductName();
    }

}
