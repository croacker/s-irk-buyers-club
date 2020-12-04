package com.croacker.buyersclub.service.mapper.shop;

import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ShopToDtoMapper implements Mapper<Shop, ShopDto> {

    @Override
    public ShopDto map(Shop input) {
        return new ShopDto()
                .setId(input.getId())
                .setName(input.getName())
                .setAddress(input.getAddress())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}