package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineInfoDto;
import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CashCheckDtoToTelegramFileProcessResult implements Mapper<CashCheckInfoDto, TelegramFileProcessResult> {

    private final DateTimeService dateTimeService;

    @Override
    public TelegramFileProcessResult map(CashCheckInfoDto input) {
        StringBuilder sb = new StringBuilder();
        sb.append("Чек:").append(toString(input.getCheckDate())).append(" ").append(input.getFiscalDocumentNumber())
                .append(", касссир:").append(input.getCashierName())
                .append("\r\n");
        for (int i=0; i< input.getCheckLines().size(); i++){
            CashCheckLineInfoDto line = input.getCheckLines().get(i);
            sb.append(i)
                    .append(". ")
                    .append(line.getProductName())
                    .append(" - ")
                    .append(line.getQuantity())
                    .append(" * ")
                    .append(line.getPrice())
                    .append(" = ")
                    .append(line.getTotalSum())
                    .append("\r\n");
        }

        return new TelegramFileProcessResult()
                .setCheckInfo(sb.toString());
    }

    private String toString(LocalDateTime dateTime){
        return dateTimeService.localDateTimeToString(dateTime);
    }

}
