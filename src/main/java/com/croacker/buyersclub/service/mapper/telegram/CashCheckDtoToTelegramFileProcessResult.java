package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CashCheckDtoToTelegramFileProcessResult implements Mapper<CashCheckDto, TelegramFileProcessResult> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:SS");

    @Override
    public TelegramFileProcessResult map(CashCheckDto input) {
        return new TelegramFileProcessResult()
                .setCheckInfo(toString(input.getCheckDate()) + " " + input.getFiscalDocumentNumber());
    }

    private String toString(LocalDateTime dateTime){
        return dateTime.format(formatter);
    }

}
