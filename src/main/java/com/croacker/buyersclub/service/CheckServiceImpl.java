package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.CheckRepo;
import com.croacker.buyersclub.service.dto.check.AddCheckDto;
import com.croacker.buyersclub.service.dto.check.CheckDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис чеков.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CheckServiceImpl implements CheckService{

    private final CheckRepo repo;

    @Override
    public List<CheckDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public CheckDto findOne(Long id) {
        return null;
    }

    @Override
    public CheckDto save(AddCheckDto dto) {
        return null;
    }

    @Override
    public CheckDto update(CheckDto dto) {
        return null;
    }

    @Override
    public CheckDto delete(Long id) {
        return null;
    }
}
