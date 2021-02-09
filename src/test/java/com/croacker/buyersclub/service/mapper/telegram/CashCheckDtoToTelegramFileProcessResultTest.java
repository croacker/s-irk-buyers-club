package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.DateTimeService;
import com.croacker.buyersclub.service.DateTimeServiceImpl;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class CashCheckDtoToTelegramFileProcessResultTest {

    private CashCheckDtoToTelegramFileProcessResult mapper;

    private DateTimeService dateTimeService;

    @BeforeEach
    void setup(){
        dateTimeService = new DateTimeServiceImpl();
        mapper = new CashCheckDtoToTelegramFileProcessResult(dateTimeService);
    }

    @Test
    void shouldMap(){
        //given
        var given = createDto();
        var expected = createEntity();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private TelegramFileProcessResult createEntity() {
        return new TelegramFileProcessResult()
                .setCheckInfo("22-11-2020 23:34:41 test_number");
    }

    private CashCheckDto createDto() {
        return new CashCheckDto()
                .setCheckDate(LocalDateTime.of(2020, 11, 22, 23, 34, 41))
                .setFiscalDocumentNumber("test_number");
    }
}