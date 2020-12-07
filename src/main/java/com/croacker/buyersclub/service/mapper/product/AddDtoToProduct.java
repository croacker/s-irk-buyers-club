package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToProduct implements Mapper<AddProductDto, Product> {

    @Override
    public Product map(AddProductDto input) {
        return new Product()
                .setName(input.getName());
    }

}
