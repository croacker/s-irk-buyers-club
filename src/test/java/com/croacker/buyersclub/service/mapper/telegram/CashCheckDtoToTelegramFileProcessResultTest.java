package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CashCheckDtoToTelegramFileProcessResultTest {

    @Autowired
    private CashCheckDtoToTelegramFileProcessResult mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

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
        return testEntitiesProducer.createTelegramFileProcessResult(0L);
    }

    private CashCheckDto createDto() {
        return testEntitiesProducer.createCashCheckDto(0L);
    }
}