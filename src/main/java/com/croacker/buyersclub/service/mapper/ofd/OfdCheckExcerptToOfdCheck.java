package com.croacker.buyersclub.service.mapper.ofd;

import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.mapper.Mapper;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.excerpt.OfdCheckExcerpt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OfdCheckExcerptToOfdCheck implements Mapper<OfdCheckExcerpt, OfdCheck> {

    private final DateTimeService dateTimeService;

    @Override
    public OfdCheck map(OfdCheckExcerpt input) {
        return new OfdCheck()
                .setUser(input.getUser())
                .setRetailPlaceAddress(input.getRetailPlaceAddress())
                .setUserInn(input.getUserInn())
                .setRequestNumber(input.getRequestNumber())
                .setShiftNumber(input.getShiftNumber())
                .setOperator(input.getOperator())
                .setOperationType(input.getOperationType())
                .setTotalSum(input.getTotalSum())
                .setCashTotalSum(input.getCashTotalSum())
                .setEcashTotalSum(input.getEcashTotalSum())
                .setKktRegId(input.getKktRegId())
                .setKktNumber(input.getKktNumber())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setFiscalSign(input.getFiscalSign())
                .setNdsNo(input.getNdsNo())
                .setNds0(input.getNds0())
                .setNds10(input.getNds10())
                .setNdsCalculated10(input.getNdsCalculated10())
                .setNds18(input.getNds18())
                .setNdsCalculated18(input.getNdsCalculated18())
                .setTaxationType(input.getTaxationType())
                .setItems(input.getItems())
                .setDiscount(input.getDiscount())
                .setDiscountSum(input.getDiscountSum())
                .setMarkup(input.getMarkup())
                .setMarkupSum(input.getMarkupSum())
                .setDateTime(dateTimeToEpoch(stringToLocalDateTime(input.getDateTime())));
    }

    private LocalDateTime stringToLocalDateTime(String str){
        return dateTimeService.stringToLocalDateTime(str);
    }

    private int dateTimeToEpoch(LocalDateTime date){
        return dateTimeService.dateTimeToEpoch(date);
    }
}
