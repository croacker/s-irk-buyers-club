package com.croacker.buyersclub.service.mapper.productgroup;

import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupToDtoMapper implements Mapper<ProductGroup, ProductGroupDto> {

    @Override
    public ProductGroupDto map(ProductGroup input) {
        return new ProductGroupDto()
                .setId(input.getId())
                .setName(input.getName())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}