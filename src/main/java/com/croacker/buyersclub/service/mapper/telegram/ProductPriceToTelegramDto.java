package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.domain.ProductPriceView;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.format.NumberService;
import com.croacker.buyersclub.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPriceToTelegramDto implements Mapper<ProductPriceView, TelegramProductPriceDto> {

    private final NumberService numberService;

    @Override
    public TelegramProductPriceDto map(ProductPriceView input) {
        return new TelegramProductPriceDto()
                .setPriceId(input.getId())
                .setProductId(input.getProductId())
                .setProductName(input.getProductName())
                .setShopId(input.getShopId())
                .setShopName(input.getShopName())
                .setPrice(priceToString(input));
    }

    private String priceToString(ProductPriceView productPrice){
        return numberService.toCurrency(productPrice.getPrice());
    }

}
