package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
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
@RequestMapping("/api/v1/product-price")
@AllArgsConstructor
@Slf4j
public class ProductPriceController implements ProductPriceOperations {

    private final ProductPriceService service;

    @Override
    public Flux<ProductPriceInfoDto> getAllProductPrices(int page, int size, String sort, Sort.Direction direction){
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<ProductPriceInfoDto> getProductPrice(@PathVariable Long id){
        return Mono.just(service.findOne(id));
    }

    @Override
    public Mono<ProductPriceDto> createProductPrice(@RequestBody AddProductPriceDto dto){
        return Mono.just(service.save(dto));
    }

    @Override
    public Mono<ProductPriceDto> updateProductPrice(@RequestBody ProductPriceDto dto){
        return Mono.just(service.update(dto));
    }

    @Override
    public Mono<ProductPriceDto> deleteProductPrice(@PathVariable Long id){
        return Mono.just(service.delete(id));
    }

}
