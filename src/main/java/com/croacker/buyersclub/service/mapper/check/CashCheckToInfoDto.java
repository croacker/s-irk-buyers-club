package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineInfoDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import com.croacker.buyersclub.service.mapper.checkline.CashCheckLineToInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CashCheckToInfoDto implements Mapper<CashCheck, CashCheckInfoDto> {

    private final CashCheckLineToInfoDto lineMapper;

    @Override
    public CashCheckInfoDto map(CashCheck input) {
        return new CashCheckInfoDto()
                .setId(input.getId())
                .setCashierId(input.getCashier().getId())
                .setCashierName(input.getCashier().getName())
                .setCheckLines(toCheckLines(input.getCheckLines()))
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

    private List<CashCheckLineInfoDto> toCheckLines(List<CashCheckLine> checkLines) {
        return checkLines.stream().map(lineMapper).collect(Collectors.toList());
    }

}
