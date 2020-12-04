package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/shop")
@AllArgsConstructor
@Slf4j
public class ShopController implements ShopOperations {

    private final ShopService service;

    @Override
    public Flux<ShopDto> getAllShops(int page, int size, String sort, Sort.Direction direction){
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<ShopDto> getShop(@PathVariable Long id){
        return Mono.just(service.findOne(id));
    }

    @Override
    public Mono<ShopDto> createShop(@RequestBody AddShopDto dto){
        return Mono.just(service.save(dto));
    }

    @Override
    public Mono<ShopDto> updateShop(@RequestBody ShopDto dto){
        return Mono.just(service.update(dto));
    }

    @Override
    public Mono<ShopDto> deleteShop(@PathVariable Long id){
        return Mono.just(service.delete(id));
    }

}
