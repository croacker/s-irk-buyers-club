package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceToTelegramDto implements Mapper<ProductPrice, TelegramProductPriceDto> {

    @Override
    public TelegramProductPriceDto map(ProductPrice input) {
        return new TelegramProductPriceDto()
                .setShop(input.getShop().getName())
                .setName(input.getProduct().getName())
                .setPrice(priceToString(input));
    }

    private String priceToString(ProductPrice productPrice){
        var price = (double)productPrice.getPrice()/100d;
        return String.valueOf(price);
    }

}
