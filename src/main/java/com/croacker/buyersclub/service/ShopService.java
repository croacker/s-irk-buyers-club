package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Сервис магазинов.
 */
public interface ShopService {

    Flux<ShopDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<ShopDto> findOne(Long id);

    Mono<ShopDto> findByName(String name);

    Mono<ShopDto> findByAddress(String address);

    Mono<ShopDto> save(AddShopDto dto);

    Mono<ShopDto> update(ShopDto dto);

    Mono<ShopDto> delete(Long id);

    Flux<ShopDto> getShops(String expression, Pageable pageable);
}
