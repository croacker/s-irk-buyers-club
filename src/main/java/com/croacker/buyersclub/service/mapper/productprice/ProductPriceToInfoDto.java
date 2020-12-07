package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceToInfoDto implements Mapper<ProductPrice, ProductPriceInfoDto> {

    @Override
    public ProductPriceInfoDto map(ProductPrice input) {
        return new ProductPriceInfoDto()
                .setId(input.getId())
                .setShopId(input.getShop().getId())
                .setShopName(input.getShop().getName())
                .setProductId(input.getProduct().getId())
                .setProductName(input.getProduct().getName())
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate());
    }

}
