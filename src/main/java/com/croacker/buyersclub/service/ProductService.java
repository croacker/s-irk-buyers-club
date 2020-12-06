package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductService {

    List<ProductDto> findAll(Pageable pageable);

    ProductDto findOne(Long id);

    ProductDto findByName(String name);

    ProductDto save(AddProductDto dto);

    ProductDto update(ProductDto dto);

    ProductDto delete(Long id);
}
