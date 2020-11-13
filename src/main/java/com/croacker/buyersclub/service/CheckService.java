package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.check.AddCheckDto;
import com.croacker.buyersclub.service.dto.check.CheckDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CheckService {

    List<CheckDto> findAll(Pageable pageable);

    CheckDto findOne(Long id);

    CheckDto save(AddCheckDto dto);

    CheckDto update(CheckDto dto);

    CheckDto delete(Long id);

}
