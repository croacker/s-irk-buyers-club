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

import java.util.List;
import java.util.stream.Collectors;

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
        return repo.findByDeletedIsFalse(pageable)
                .stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ShopDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto findByName(String name) {
        return repo.findFirstByName(name).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto findByAddress(String address) {
        return repo.findFirstByAddress(address).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto save(AddShopDto dto) {
        var organization = organizationRepo.findById(dto.getOrganizationId()).get();
        var shop = addToEntityMapper.map(dto)
                .setOrganization(organization).setDeleted(false);
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public ShopDto update(ShopDto dto) {
        var organization = organizationRepo.findById(dto.getOrganizationId()).get();
        var shop = toShopMapper.map(dto)
                .setOrganization(organization);
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public ShopDto delete(Long id) {
        return repo.findById(id).map(organization -> {
            organization.setDeleted(true);
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
