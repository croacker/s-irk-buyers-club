package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.OrganizationRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.mapper.organization.AddDtoToOrganizationMapper;
import com.croacker.buyersclub.service.mapper.organization.DtoToOrganizationMapper;
import com.croacker.buyersclub.service.mapper.organization.OrganizationToDtoMapper;
import com.croacker.buyersclub.service.mapper.product.AddDtoToProductMapper;
import com.croacker.buyersclub.service.mapper.product.DtoToProductMapper;
import com.croacker.buyersclub.service.mapper.product.ProductToDtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepo repo;

    private final ProductToDtoMapper toDtoMapper;

    private final DtoToProductMapper toProductMapper;

    private final AddDtoToProductMapper addToProductMapper;

    @Override
    public List<ProductDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ProductDto findOne(Long id) {
        return null;
    }

    @Override
    public ProductDto save(AddProductDto dto) {
        return null;
    }

    @Override
    public ProductDto update(ProductDto dto) {
        return null;
    }

    @Override
    public ProductDto delete(Long id) {
        return null;
    }
}
