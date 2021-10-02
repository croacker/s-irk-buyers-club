package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductPriceService {

    List<ProductPriceInfoDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    ProductPriceInfoDto findOne(Long id);

    List<ProductPriceInfoDto> findByProduct(Long id);

    ProductPriceDto findPrice(ProductDto product, ShopDto shop, LocalDateTime dateTime);

    ProductPriceDto save(AddProductPriceDto dto);

    ProductPriceDto update(ProductPriceDto dto);

    ProductPriceDto delete(Long id);

    Long getProductsPricesCount(String expression);

    List<TelegramProductPriceDto> getProductsPrices(String expression, Pageable pageable);
}
