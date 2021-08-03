package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.ProductGroupRepo;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.buyersclub.service.mapper.productgroup.AddDtoToProductGroup;
import com.croacker.buyersclub.service.mapper.productgroup.DtoToProductGroup;
import com.croacker.buyersclub.service.mapper.productgroup.ProductGroupToDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class ProductGroupServiceImpl implements ProductGroupService{

    private final ProductGroupRepo repo;

    private final ProductGroupToDto toDtoMapper;

    private final DtoToProductGroup toEntityMapper;

    private final AddDtoToProductGroup addToEntityMapper;

    @Override
    public List<ProductGroupDto> findAll(Pageable pageable) {
        return StreamSupport.stream(repo.findAll().spliterator(), false).map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public ProductGroupDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public ProductGroupDto save(AddProductGroupDto dto) {
        var product = addToEntityMapper.map(dto).setDeleted(false);
        product = repo.save(product);
        return toDtoMapper.map(product);
    }

    @Override
    public ProductGroupDto update(ProductGroupDto dto) {
        var product = toEntityMapper.map(dto);
        product = repo.save(product);
        return toDtoMapper.map(product);
    }

    @Override
    public ProductGroupDto delete(Long id) {
        return repo.findById(id).map(organization -> {
            organization.setDeleted(true);
            organization = repo.save(organization);
            return toDtoMapper.map(organization);
        }).orElse(null);
    }
}
