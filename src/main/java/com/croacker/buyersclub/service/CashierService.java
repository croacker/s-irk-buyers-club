package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CashierService {

    List<CashierDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    CashierDto findOne(Long id);

    CashierDto findByNameAndShopId(String name, Long shopId);

    CashierDto save(AddCashierDto dto);

    CashierDto update(CashierDto dto);

    CashierDto delete(Long id);

}
