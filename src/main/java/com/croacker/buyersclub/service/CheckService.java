package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CheckService {

    Flux<CashCheckInfoDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<CashCheckInfoDto> findById(Long id);

    Mono<CashCheckDto> findCheck(String kktRegId, String fiscalDriveNumber, String fiscalDocumentNumber);

    Mono<CashCheckDto> save(AddCashCheckDto dto);

    Mono<CashCheckDto> update(CashCheckDto dto);

    Mono<CashCheckDto> delete(Long id);

}
