package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CheckService {

    List<CashCheckDto> findAll(Pageable pageable);

    CashCheckDto findOne(Long id);

    CashCheckDto save(AddCashCheckDto dto);

    CashCheckDto update(CashCheckDto dto);

    CashCheckDto delete(Long id);

}
