package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductPriceService {

    List<ProductPriceInfoDto> findAll(Pageable pageable);

    ProductPriceDto findOne(Long id);

    ProductPriceInfoDto findByProduct(String name);

    ProductPriceDto save(AddProductPriceDto dto);

    ProductPriceDto update(ProductPriceDto dto);

    ProductPriceDto delete(Long id);
}
