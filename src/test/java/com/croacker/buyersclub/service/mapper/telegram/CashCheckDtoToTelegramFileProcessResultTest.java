package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CashCheckDtoToTelegramFileProcessResultTest {

    @Autowired
    private CashCheckDtoToTelegramFileProcessResult mapper;

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