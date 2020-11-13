package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.CashierRepo;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис кассиров.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CashierServiceImpl implements CashierService {

    private final CashierRepo repo;

    @Override
    public List<CashierDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public CashierDto findOne(Long id) {
        return null;
    }

    @Override
    public CashierDto save(AddCashierDto dto) {
        return null;
    }

    @Override
    public CashierDto update(CashierDto dto) {
        return null;
    }

    @Override
    public CashierDto delete(Long id) {
        return null;
    }
}
