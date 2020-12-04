package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.CheckRepo;
import com.croacker.buyersclub.service.dto.check.AddCheckDto;
import com.croacker.buyersclub.service.dto.check.CheckDto;
import com.croacker.buyersclub.service.mapper.cashier.AddDtoToCashierMapper;
import com.croacker.buyersclub.service.mapper.cashier.CashierToDtoMapper;
import com.croacker.buyersclub.service.mapper.cashier.DtoToCashierMapper;
import com.croacker.buyersclub.service.mapper.check.AddDtoToCheckMapper;
import com.croacker.buyersclub.service.mapper.check.CheckToDtoMapper;
import com.croacker.buyersclub.service.mapper.check.DtoToCheckMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис чеков.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CheckServiceImpl implements CheckService{

    private final CheckRepo repo;

    private final CheckToDtoMapper toDtoMapper;

    private final DtoToCheckMapper toEntityMapper;

    private final AddDtoToCheckMapper addToEntityMapper;

    @Override
    public List<CheckDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public CheckDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public CheckDto save(AddCheckDto dto) {
        var check = addToEntityMapper.map(dto)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setDeleted(false);
        check = repo.save(check);
        return toDtoMapper.map(check);
    }

    @Override
    public CheckDto update(CheckDto dto) {
        var check = toEntityMapper.map(dto)
                .setUpdatedAt(LocalDateTime.now());
        check = repo.save(check);
        return toDtoMapper.map(check);
    }

    @Override
    public CheckDto delete(Long id) {
        return null;
    }
}
