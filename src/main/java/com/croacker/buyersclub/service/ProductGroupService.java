package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductGroupService {

    List<ProductGroupDto> findAll(Pageable pageable);

    ProductGroupDto findOne(Long id);

    ProductGroupDto save(AddProductGroupDto dto);

    ProductGroupDto update(ProductGroupDto dto);

    ProductGroupDto delete(Long id);

}
