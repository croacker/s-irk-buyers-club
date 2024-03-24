package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductGroupService {

    Flux<ProductGroupDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<ProductGroupDto> findOne(Long id);

    Mono<ProductGroupDto> save(AddProductGroupDto dto);

    Mono<ProductGroupDto> update(ProductGroupDto dto);

    Mono<ProductGroupDto> delete(Long id);

}
