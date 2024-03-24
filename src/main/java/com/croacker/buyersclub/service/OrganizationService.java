package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;

/**
 * Сервис организаций.
 */
public interface OrganizationService {

    Flux<OrganizationDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<OrganizationDto> findOne(Long id);

    Mono<OrganizationDto> save(AddOrganizationDto dto);

    Mono<OrganizationDto> update(OrganizationDto dto);

    Mono<OrganizationDto> delete(Long id);

    Mono<OrganizationDto> findByInn(String inn);

    Flux<OrganizationDto> getOrganizations(String expression, Pageable pageable);
}
