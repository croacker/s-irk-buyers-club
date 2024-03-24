package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductPriceService {

    Flux<ProductPriceInfoDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<ProductPriceInfoDto> findOne(Long id);

    Flux<ProductPriceInfoDto> findByProduct(Long id);

    Mono<ProductPriceDto> findPrice(ProductDto product, ShopDto shop, LocalDateTime dateTime);

    Mono<ProductPriceDto> save(AddProductPriceDto dto);

    Mono<ProductPriceDto> update(ProductPriceDto dto);

    Mono<ProductPriceDto> delete(Long id);

    Mono<Long> getProductsPricesCount(String expression);

    Flux<TelegramProductPriceDto> getProductsPrices(String expression, Pageable pageable);
}
