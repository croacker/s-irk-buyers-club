package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductPriceService {

    List<ProductPriceDto> findAll(Pageable pageable);

    ProductPriceDto findOne(Long id);

    List<ProductPriceDto> findByProduct(String name);

    ProductPriceDto findPrice(ProductDto product, ShopDto shop, LocalDateTime dateTime);

    ProductPriceDto save(AddProductPriceDto dto);

    ProductPriceDto update(ProductPriceDto dto);

    ProductPriceDto delete(Long id);
}
