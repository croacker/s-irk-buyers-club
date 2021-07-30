package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceToDto implements Mapper<ProductPrice, ProductPriceDto> {

    @Override
    public ProductPriceDto map(ProductPrice input) {
        return new ProductPriceDto()
                .setId(input.getId())
                .setShopId(input.getShop().getId())
                .setProductId(input.getProduct().getId())
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate())
                .setDeleted(input.getDeleted());
    }

}
