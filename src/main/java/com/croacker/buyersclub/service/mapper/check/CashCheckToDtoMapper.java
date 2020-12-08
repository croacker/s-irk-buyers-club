package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CashCheckToDtoMapper implements Mapper<CashCheck, CashCheckDto> {

    @Override
    public CashCheckDto map(CashCheck input) {
        return new CashCheckDto()
                .setCashierId(input.getCashier().getId())
                .setRequestNumber(input.getRequestNumber())
                .setShiftNumber(input.getShiftNumber())
                .setKktRegId(input.getKktRegId())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setTotalSum(input.getTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setCheckDate(input.getCheckDate())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}
