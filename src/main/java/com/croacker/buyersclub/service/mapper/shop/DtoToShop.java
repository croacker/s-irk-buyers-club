package com.croacker.buyersclub.service.mapper.shop;

import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToShop implements Mapper<ShopDto, Shop> {

    @Override
    public Shop map(ShopDto input) {
        return new Shop()
                .setId(input.getId())
                .setName(input.getName())
                .setAddress(input.getAddress())
                .setDeleted(input.getDeleted());
    }

}
