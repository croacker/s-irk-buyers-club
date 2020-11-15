package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController implements ProductOperations {

    private final ProductService service;

    @Override
    public Flux<ProductDto> getAllProducts(int page, int size, String sort, Sort.Direction direction){
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<ProductDto> getProduct(@PathVariable Long id){
        return Mono.just(service.findOne(id));
    }

    @Override
    public Mono<ProductDto> createProduct(@RequestBody AddProductDto dto){
        return Mono.just(service.save(dto));
    }

    @Override
    public Mono<ProductDto> updateProduct(@RequestBody ProductDto dto){
        return Mono.just(service.update(dto));
    }

    @Override
    public Mono<ProductDto> deleteProduct(@PathVariable Long id){
        return Mono.just(service.delete(id));
    }

}
