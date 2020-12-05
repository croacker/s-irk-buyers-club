package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToCashCheckMapper implements Mapper<AddCashCheckDto, CashCheck> {

    @Override
    public CashCheck map(AddCashCheckDto input) {
        return new CashCheck()
                .setKktRegId(input.getKktRegId())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setTotalSum(input.getTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setCheckDate(input.getCheckDate());
    }

}