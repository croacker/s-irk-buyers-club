package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductToDto implements Mapper<Product, ProductDto> {

    @Override
    public ProductDto map(Product input) {
        return new ProductDto()
                .setId(input.getId())
                .setName(input.getName())
                .setProductGroupId(getProductGroupId(input))
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

    private Long getProductGroupId(Product input) {
        Long result = null;
        if (input.getProductGroup() != null) {
            result = input.getProductGroup().getId();
        }
        return result;
    }
}
