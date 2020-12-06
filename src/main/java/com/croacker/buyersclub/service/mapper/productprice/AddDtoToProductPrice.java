package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToProductPrice implements Mapper<AddProductPriceDto, ProductPrice> {

    @Override
    public ProductPrice map(AddProductPriceDto input) {
        return new ProductPrice()
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate());
    }

}
