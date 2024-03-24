package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductService {

    Flux<ProductDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<ProductDto> findOne(Long id);

    Mono<ProductDto> findByName(String name);

    Mono<ProductDto> save(AddProductDto dto);

    Mono<ProductDto> update(ProductDto dto);

    Mono<ProductDto> delete(Long id);
}
