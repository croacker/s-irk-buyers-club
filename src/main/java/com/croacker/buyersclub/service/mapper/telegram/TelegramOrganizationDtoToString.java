package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TelegramOrganizationDtoToString implements Mapper<OrganizationDto, String> {

    @Override
    public String map(OrganizationDto input) {
        return "[" + input.getName() + ", " + input.getInn() + "]";
    }

}
