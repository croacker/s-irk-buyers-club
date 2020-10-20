package com.croacker.buyersclub.service.mapper.organization;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddDtoToOrganizationMapper implements Mapper<AddOrganizationDto, Organization> {
    @Override
    public Organization map(AddOrganizationDto input) {
        return new Organization()
                .setName(input.getName())
                .setInn(input.getInn());
    }
}
