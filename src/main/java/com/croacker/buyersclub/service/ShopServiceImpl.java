package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.OrganizationRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.shop.AddDtoToShop;
import com.croacker.buyersclub.service.mapper.shop.DtoToShop;
import com.croacker.buyersclub.service.mapper.shop.ShopToDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService{

    private final ShopRepo repo;

    private final OrganizationRepo organizationRepo;

    private final ShopToDto toDtoMapper;

    private final DtoToShop toShopMapper;

    private final AddDtoToShop addToEntityMapper;

    @Override
    public Flux<ShopDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable)
                .stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public Mono<ShopDto> findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public Mono<ShopDto> findByName(String name) {
        return repo.findFirstByName(name).map(toDtoMapper).orElse(null);
    }

    @Override
    public Mono<ShopDto> findByAddress(String address) {
        return repo.findFirstByAddress(address).map(toDtoMapper).orElse(null);
    }

    @Override
    public Mono<ShopDto> save(AddShopDto dto) {
        var organization = organizationRepo.findById(dto.getOrganizationId()).get();
        var shop = addToEntityMapper.map(dto)
                .setOrganization(organization).setDeleted(false);
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public Mono<ShopDto> update(ShopDto dto) {
        var organization = organizationRepo.findById(dto.getOrganizationId()).get();
        var shop = toShopMapper.map(dto)
                .setOrganization(organization);
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public Mono<ShopDto> delete(Long id) {
        return repo.findById(id).map(shop -> {
            shop.setDeleted(true);
            shop = repo.save(shop);
            return toDtoMapper.map(shop);
        }).orElse(null);
    }

    @Override
    public Flux<ShopDto> getShops(String expression, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(expression, pageable)
                .stream().map(toDtoMapper).collect(Collectors.toList());
    }
}
