package com.croacker.buyersclub.service.mapper.shop;

import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToShopMapper implements Mapper<AddShopDto, Shop> {

    @Override
    public Shop map(AddShopDto input) {
        return new Shop()
                .setName(input.getName())
                .setAddress(input.getAddress());
    }

}
