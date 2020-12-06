package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.CheckRepo;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.mapper.check.AddDtoToCashCheckMapper;
import com.croacker.buyersclub.service.mapper.check.CashCheckToDtoMapper;
import com.croacker.buyersclub.service.mapper.check.DtoToCashCheckMapper;
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

    private final CashCheckToDtoMapper toDtoMapper;

    private final DtoToCashCheckMapper toEntityMapper;

    private final AddDtoToCashCheckMapper addToEntityMapper;

    @Override
    public List<CashCheckDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public CashCheckDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public CashCheckDto save(AddCashCheckDto dto) {
        var check = addToEntityMapper.map(dto)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setDeleted(false);
        check = repo.save(check);
        return toDtoMapper.map(check);
    }

    @Override
    public CashCheckDto update(CashCheckDto dto) {
        var check = toEntityMapper.map(dto)
                .setUpdatedAt(LocalDateTime.now());
        check = repo.save(check);
        return toDtoMapper.map(check);
    }

    @Override
    public CashCheckDto delete(Long id) {
        return null;
    }
}
