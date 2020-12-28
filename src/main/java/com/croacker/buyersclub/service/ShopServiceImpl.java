package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.OrganizationRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.shop.AddDtoToShopMapper;
import com.croacker.buyersclub.service.mapper.shop.DtoToShopMapper;
import com.croacker.buyersclub.service.mapper.shop.ShopToDtoMapper;
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
public class ShopServiceImpl implements ShopService{

    private final ShopRepo repo;

    private final OrganizationRepo organizationRepo;

    private final ShopToDtoMapper toDtoMapper;

    private final DtoToShopMapper toShopMapper;

    private final AddDtoToShopMapper addToEntityMapper;

    @Override
    public List<ShopDto> findAll(Pageable pageable) {
        return StreamSupport.stream(repo.findAll().spliterator(), false).map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ShopDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto findByName(String name) {
        return repo.findByName(name).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto findByAddress(String address) {
        return repo.findByAddress(address).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto save(AddShopDto dto) {
        var organization = organizationRepo.findById(dto.getOrganizationId()).get();
        var shop = addToEntityMapper.map(dto)
                .setOrganization(organization)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setDeleted(false);
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public ShopDto update(ShopDto dto) {
        var shop = toShopMapper.map(dto)
                .setUpdatedAt(LocalDateTime.now());
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public ShopDto delete(Long id) {
        return repo.findById(id).map(organization -> {
            organization.setUpdatedAt(LocalDateTime.now())
                    .setDeleted(true);
            organization = repo.save(organization);
            return toDtoMapper.map(organization);
        }).orElse(null);
    }

    @Override
    public List<ShopDto> getShops(String expression) {
        return repo.findByNameContainingIgnoreCase(expression)
                .stream().map(toDtoMapper).collect(Collectors.toList());
    }
}
