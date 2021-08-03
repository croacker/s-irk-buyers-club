package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.CashierRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.mapper.cashier.AddDtoToCashier;
import com.croacker.buyersclub.service.mapper.cashier.CashierToDto;
import com.croacker.buyersclub.service.mapper.cashier.DtoToCashier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис кассиров.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CashierServiceImpl implements CashierService {

    private final CashierRepo repo;

    private final ShopRepo shopRepo;

    private final CashierToDto toDtoMapper;

    private final DtoToCashier toEntityMapper;

    private final AddDtoToCashier addToEntityMapper;

    @Override
    public List<CashierDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public CashierDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public CashierDto findByName(String name) {
        return repo.findByName(name).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public CashierDto save(AddCashierDto dto) {
        var shop = shopRepo.findById(dto.getShopId()).get();
        var cashier = addToEntityMapper.map(dto)
                .setShop(shop)
                .setDeleted(false);
        cashier = repo.save(cashier);
        return toDtoMapper.map(cashier);
    }

    @Override
    public CashierDto update(CashierDto dto) {
        var shop = shopRepo.findById(dto.getShopId()).get();
        var cashier = toEntityMapper.map(dto).setShop(shop);
        cashier = repo.save(cashier);
        return toDtoMapper.map(cashier);
    }

    @Override
    public CashierDto delete(Long id) {
        return repo.findById(id).map(cashier -> {
            cashier.setDeleted(true);
            cashier = repo.save(cashier);
            return toDtoMapper.map(cashier);
        }).orElse(null);
    }
}
