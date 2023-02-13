package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineInfoDto;
import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CashCheckDtoToTelegramFileProcessResult implements Mapper<CashCheckInfoDto, TelegramFileProcessResult> {

    private final DateTimeService dateTimeService;

    private final LocaleService localeService;

    public static final String LINE_SEPARATOR = "\r\n";

    @Override
    public TelegramFileProcessResult map(CashCheckInfoDto input) {
        StringBuilder sb = new StringBuilder();
        sb.append(getString("response.check.title")).append(":")
                .append(input.getFiscalDocumentNumber())
                .append(" ").append(getString("response.check.date")).append(" ").append(toString(input.getCheckDate()))
                .append(", ").append(getString("response.check.cashier")).append(":").append(input.getCashierName())
                .append(LINE_SEPARATOR);
        for (int i = 0; i < input.getCheckLines().size(); i++) {
            var line = input.getCheckLines().get(i);
            sb.append(i+1)
                    .append(". ")
                    .append(line.getProductName())
                    .append(" - ")
                    .append(toQuantity(line.getQuantity())).append(getString("response.check.units.pcs"))
                    .append(" * ")
                    .append(toPrice(line.getPrice())).append(getString("response.check.currency"))
                    .append(" = ")
                    .append(toSumm(line.getTotalSum())).append(getString("response.check.currency"))
                    .append(LINE_SEPARATOR);
        }

        String checkInfo = replaceMarkdown(sb.toString());
        return new TelegramFileProcessResult()
                .setCheckInfo(checkInfo);
    }

    private String replaceMarkdown(String checkInfo) {
        return checkInfo.replace("_", "\\_")
                .replace("*", "\\*")
                .replace("[", "\\[")
                .replace("`", "\\`");
    }

    private String toString(LocalDateTime dateTime){
        return dateTimeService.localDateTimeToString(dateTime);
    }

    private double toQuantity(int quantity){
        return (double)quantity/1000;
    }

    private double toPrice(int price){
        return (double)price/100;
    }

    private double toSumm(int summ){
        return (double)summ/100;
    }

    private String getString(String key){
        return localeService.getString(key);
    }
}
