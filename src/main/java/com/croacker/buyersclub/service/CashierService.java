package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CashierService {

    Flux<CashierDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<CashierDto> findOne(Long id);

    Mono<CashierDto> findByNameAndShopId(String name, Long shopId);

    Mono<CashierDto> save(AddCashierDto dto);

    Mono<CashierDto> update(CashierDto dto);

    Mono<CashierDto> delete(Long id);

}
