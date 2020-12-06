package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.CashierRepo;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.mapper.cashier.AddDtoToCashierMapper;
import com.croacker.buyersclub.service.mapper.cashier.CashierToDtoMapper;
import com.croacker.buyersclub.service.mapper.cashier.DtoToCashierMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private final CashierToDtoMapper toDtoMapper;

    private final DtoToCashierMapper toEntityMapper;

    private final AddDtoToCashierMapper addToEntityMapper;

    @Override
    public List<CashierDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public CashierDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public CashierDto findByName(String name) {
        return repo.findByName(name).map(toDtoMapper).orElse(null);
    }

    @Override
    public CashierDto save(AddCashierDto dto) {
        var cashier = addToEntityMapper.map(dto)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setDeleted(false);
        cashier = repo.save(cashier);
        return toDtoMapper.map(cashier);
    }

    @Override
    public CashierDto update(CashierDto dto) {
        var cashier = toEntityMapper.map(dto)
                .setUpdatedAt(LocalDateTime.now());
        cashier = repo.save(cashier);
        return toDtoMapper.map(cashier);
    }

    @Override
    public CashierDto delete(Long id) {
        return repo.findById(id).map(cashier -> {
            cashier.setUpdatedAt(LocalDateTime.now())
                    .setDeleted(true);
            cashier = repo.save(cashier);
            return toDtoMapper.map(cashier);
        }).orElse(null);
    }
}
