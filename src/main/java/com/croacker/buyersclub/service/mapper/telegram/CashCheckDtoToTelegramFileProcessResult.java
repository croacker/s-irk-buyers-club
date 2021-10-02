package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CashCheckDtoToTelegramFileProcessResult implements Mapper<CashCheckDto, TelegramFileProcessResult> {

    private final DateTimeService service;

    @Override
    public TelegramFileProcessResult map(CashCheckDto input) {
        return new TelegramFileProcessResult()
                .setCheckInfo(toString(input.getCheckDate()) + " " + input.getFiscalDocumentNumber());
    }

    private String toString(LocalDateTime dateTime){
        return service.localDateTimeToString(dateTime);
    }

}
