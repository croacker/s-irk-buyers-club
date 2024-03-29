package com.croacker.buyersclub.service.mapper.productgroup;

import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToProductGroup implements Mapper<ProductGroupDto, ProductGroup> {

    @Override
    public ProductGroup map(ProductGroupDto input) {
        return new ProductGroup()
                .setId(input.getId())
                .setName(input.getName())
                .setDeleted(input.getDeleted());
    }

}
