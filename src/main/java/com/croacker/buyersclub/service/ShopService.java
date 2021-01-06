package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

/**
 * Сервис магазинов.
 */
public interface ShopService {

    List<ShopDto> findAll(Pageable pageable);

    ShopDto findOne(Long id);

    ShopDto findByName(String name);

    ShopDto findByAddress(String address);

    ShopDto save(AddShopDto dto);

    ShopDto update(ShopDto dto);

    ShopDto delete(Long id);

    List<ShopDto> getShops(String expression);
}
