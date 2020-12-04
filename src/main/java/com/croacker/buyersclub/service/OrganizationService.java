package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис организаций.
 */
public interface OrganizationService {

    List<OrganizationDto> findAll(Pageable pageable);

    OrganizationDto findOne(Long id);

    OrganizationDto save(AddOrganizationDto dto);

    OrganizationDto update(OrganizationDto dto);

    OrganizationDto delete(Long id);

}