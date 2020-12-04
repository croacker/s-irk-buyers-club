package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.domain.Check;
import com.croacker.buyersclub.service.dto.check.CheckDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToCheckMapper implements Mapper<CheckDto, Check> {

    @Override
    public Check map(CheckDto input) {
        return new Check()
                .setKktRegId(input.getKktRegId())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setTotalSum(input.getTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setCheckDate(input.getCheckDate())
                .setDeleted(input.getDeleted());
    }

}
