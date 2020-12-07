package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.product.ProductInfoDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductDtoToInfoDto implements Mapper<ProductDto, ProductInfoDto> {
    @Override
    public ProductInfoDto map(ProductDto input) {
        return new ProductInfoDto()
                .setId(input.getId())
                .setName(input.getName())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }
}
