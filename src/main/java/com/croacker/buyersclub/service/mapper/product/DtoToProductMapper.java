package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToProductMapper implements Mapper<ProductDto, Product> {

    @Override
    public Product map(ProductDto input) {
        return new Product()
                .setId(input.getId())
                .setName(input.getName())
                .setDeleted(input.getDeleted());
    }

}
