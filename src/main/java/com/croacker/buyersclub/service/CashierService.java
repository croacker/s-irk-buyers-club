package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CashierService {

    List<CashierDto> findAll(Pageable pageable);

    CashierDto findOne(Long id);

    CashierDto findByName(String name);

    CashierDto save(AddCashierDto dto);

    CashierDto update(CashierDto dto);

    CashierDto delete(Long id);
}
