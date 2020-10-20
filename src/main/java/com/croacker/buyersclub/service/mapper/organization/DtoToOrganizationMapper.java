package com.croacker.buyersclub.service.mapper.organization;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.mapper.Mapper;

import java.time.LocalDateTime;

public class DtoToOrganizationMapper implements Mapper<OrganizationDto, Organization> {
    @Override
    public Organization map(OrganizationDto input) {
        return new Organization()
                .setId(input.getId())
                .setName(input.getName())
                .setInn(input.getInn())
                .setCreatedAt(input.getCreatedAt()) //TODO not update
                .setUpdatedAt(LocalDateTime.now())
                .setDeleted(input.getDeleted());
    }
}
