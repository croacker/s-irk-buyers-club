package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CheckService {

    List<CashCheckInfoDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    CashCheckInfoDto findOne(Long id);

    CashCheckDto save(AddCashCheckDto dto);

    CashCheckDto update(CashCheckDto dto);

    CashCheckDto delete(Long id);

}
