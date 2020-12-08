package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.CashierRepo;
import com.croacker.buyersclub.repo.CheckRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.mapper.check.AddDtoToCashCheckMapper;
import com.croacker.buyersclub.service.mapper.check.CashCheckToDtoMapper;
import com.croacker.buyersclub.service.mapper.check.CashCheckToInfoDtoMapper;
import com.croacker.buyersclub.service.mapper.check.DtoToCashCheckMapper;
import com.croacker.buyersclub.service.mapper.checkline.AddDtoToCashCheckLine;
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

    private final CashierRepo cashierRepo;

    private final ProductRepo productRepo;

    private final CashCheckToDtoMapper toDtoMapper;

    private final CashCheckToInfoDtoMapper toInfoDtoMapper;

    private final DtoToCashCheckMapper toEntityMapper;

    private final AddDtoToCashCheckMapper addToEntityMapper;

    private final AddDtoToCashCheckLine addLineToEntityMapper;

    @Override
    public List<CashCheckInfoDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toInfoDtoMapper).collect(Collectors.toList());
    }

    @Override
    public CashCheckInfoDto findOne(Long id) {
        return repo.findById(id).map(toInfoDtoMapper).orElse(null);
    }

    @Override
    public CashCheckDto save(AddCashCheckDto dto) {
        var cashier = cashierRepo.findById(dto.getCashierId()).get();
        var checkLines = dto.getCheckLines().stream().map(lineDto -> {
            var product = productRepo.findById(lineDto.getProductId()).get();
            return addLineToEntityMapper
                    .map(lineDto)
                    .setProduct(product)
                    .setDeleted(false);
        }).collect(Collectors.toList());
        var check = addToEntityMapper.map(dto)
                .setCashier(cashier)
                .setCheckLines(checkLines)
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
