package com.croacker.buyersclub.service.mapper.organization;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationToDtoMapper implements Mapper<Organization, OrganizationDto> {
    @Override
    public OrganizationDto map(Organization input) {
        return new OrganizationDto()
                .setId(input.getId())
                .setName(input.getName())
                .setInn(input.getInn())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }
}
