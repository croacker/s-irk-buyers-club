package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.domain.Check;
import com.croacker.buyersclub.service.dto.check.AddCheckDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToCheckMapper implements Mapper<AddCheckDto, Check> {

    @Override
    public Check map(AddCheckDto input) {
        return new Check()
                .setKktRegId(input.getKktRegId())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setTotalSum(input.getTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setCheckDate(input.getCheckDate());
    }

}
