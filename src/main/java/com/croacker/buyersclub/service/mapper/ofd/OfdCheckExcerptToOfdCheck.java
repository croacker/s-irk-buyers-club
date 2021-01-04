package com.croacker.buyersclub.service.mapper.ofd;

import com.croacker.buyersclub.service.mapper.Mapper;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.excerpt.OfdCheckExcerpt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class OfdCheckExcerptToOfdCheck implements Mapper<OfdCheckExcerpt, OfdCheck> {

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
                .setModifiers(input.getModifiers())
                .setStornoItems(input.getStornoItems())
                .setDateTime(dateTimeToEpoch(stringToLocalDateTime(input.getDateTime())));
    }

    // TODO в common
    private LocalDateTime stringToLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }

    // TODO в common
    private int dateTimeToEpoch(LocalDateTime date){
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        return (int) date.toEpochSecond(zoneId.getRules().getOffset(instant));
    }
}
