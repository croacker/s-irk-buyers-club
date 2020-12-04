package com.croacker.buyersclub.service.mapper.productgroup;

import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToProductGroupMapper implements Mapper<AddProductGroupDto, ProductGroup> {

    @Override
    public ProductGroup map(AddProductGroupDto input) {
        return new ProductGroup()
                .setId(input.getId())
                .setName(input.getName());
    }

}
