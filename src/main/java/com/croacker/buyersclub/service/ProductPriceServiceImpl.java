package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductPriceServiceImpl implements ProductPriceService{
    @Override
    public List<ProductPriceInfoDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ProductPriceDto findOne(Long id) {
        return null;
    }

    @Override
    public ProductPriceInfoDto findByProduct(String name) {
        return null;
    }

    @Override
    public ProductPriceDto save(AddProductPriceDto dto) {
        return null;
    }

    @Override
    public ProductPriceDto update(ProductPriceDto dto) {
        return null;
    }

    @Override
    public ProductPriceDto delete(Long id) {
        return null;
    }
}
