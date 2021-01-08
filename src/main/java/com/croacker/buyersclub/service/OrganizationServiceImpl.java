package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.OrganizationRepo;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.mapper.organization.AddDtoToOrganizationMapper;
import com.croacker.buyersclub.service.mapper.organization.DtoToOrganizationMapper;
import com.croacker.buyersclub.service.mapper.organization.OrganizationToDtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepo repo;

    private final OrganizationToDtoMapper toDtoMapper;

    private final DtoToOrganizationMapper toEntityMapper;

    private final AddDtoToOrganizationMapper addToEntityMapper;

    @Override
    public List<OrganizationDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public OrganizationDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public OrganizationDto findByInn(String inn) {
        return repo.findByInn(inn).map(toDtoMapper).orElse(null);
    }

    @Override
    public List<OrganizationDto> getOrganizations(String expression, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(expression, pageable)
                .stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public OrganizationDto save(AddOrganizationDto dto) {
        var organization = addToEntityMapper.map(dto).setDeleted(false);
        organization = repo.save(organization);
        return toDtoMapper.map(organization);
    }

    @Override
    public OrganizationDto update(OrganizationDto dto) {
        var organization = toEntityMapper.map(dto);
        organization = repo.save(organization);
        return toDtoMapper.map(organization);
    }

    @Override
    public OrganizationDto delete(Long id) {
        return repo.findById(id).map(organization -> {
            organization.setDeleted(true);
            organization = repo.save(organization);
            return toDtoMapper.map(organization);
        }).orElse(null);
    }

}
