package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/organization")
@AllArgsConstructor
@Slf4j
public class OrganizationController implements OrganizationOperations {

    private final OrganizationService service;

    @Override
    public Flux<OrganizationDto> getAllOrganizations(int page, int size, String sort, Sort.Direction direction) {
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<OrganizationDto> getOrganization(Long id) {
        return Mono.just(service.findOne(id));
    }

    @Override
    public Mono<OrganizationDto> createOrganization(AddOrganizationDto dto) {
        return Mono.just(service.save(dto));
    }

    @Override
    public Mono<OrganizationDto> updateOrganization(OrganizationDto dto) {
        return Mono.just(service.update(dto));
    }

    @Override
    public Mono<OrganizationDto> deleteOrganization(Long id) {
        return Mono.just(service.delete(id));
    }
}
