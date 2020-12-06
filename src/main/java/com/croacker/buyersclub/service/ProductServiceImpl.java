package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.mapper.product.AddDtoToProductMapper;
import com.croacker.buyersclub.service.mapper.product.DtoToProductMapper;
import com.croacker.buyersclub.service.mapper.product.ProductToDtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepo repo;

    private final ProductToDtoMapper toDtoMapper;

    private final DtoToProductMapper toEntityMapper;

    private final AddDtoToProductMapper addToEntityMapper;

    @Override
    public List<ProductDto> findAll(Pageable pageable) {
        return StreamSupport.stream(repo.findAll().spliterator(), false).map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ProductDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public ProductDto findByName(String name) {
        return repo.findByName(name).map(toDtoMapper).orElse(null);
    }

    @Override
    public ProductDto save(AddProductDto dto) {
        var product = addToEntityMapper.map(dto)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setDeleted(false);
        product = repo.save(product);
        return toDtoMapper.map(product);
    }

    @Override
    public ProductDto update(ProductDto dto) {
        var product = toEntityMapper.map(dto)
                .setUpdatedAt(LocalDateTime.now());
        product = repo.save(product);
        return toDtoMapper.map(product);
    }

    @Override
    public ProductDto delete(Long id) {
        return repo.findById(id).map(organization -> {
            organization.setUpdatedAt(LocalDateTime.now())
                    .setDeleted(true);
            organization = repo.save(organization);
            return toDtoMapper.map(organization);
        }).orElse(null);
    }
}
