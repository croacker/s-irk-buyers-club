package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToProductPrice implements Mapper<ProductPriceDto, ProductPrice> {

    @Override
    public ProductPrice map(ProductPriceDto input) {
        return new ProductPrice()
                .setId(input.getId())
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate());
    }

}
