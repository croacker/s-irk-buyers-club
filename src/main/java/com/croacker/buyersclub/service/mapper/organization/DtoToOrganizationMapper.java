package com.croacker.buyersclub.service.mapper.organization;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DtoToOrganizationMapper implements Mapper<OrganizationDto, Organization> {
    @Override
    public Organization map(OrganizationDto input) {
        return new Organization()
                .setId(input.getId())
                .setName(input.getName())
                .setInn(input.getInn())
                .setDeleted(input.getDeleted());
    }
}
