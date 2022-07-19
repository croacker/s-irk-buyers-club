package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TelegramProductPriceDtoToStringTest {

    @Autowired
    private TelegramProductPriceDtoToString mapper;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @Test
    void shouldMapEntity() {
        //given
        var given = createDto(0L);
        var expected = createString();

        // when
        var actual = mapper.map(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private String createString() {
        return  "0.00 руб. - test_product_0";
    }

    private TelegramProductPriceDto createDto(long id) {
        return testEntitiesProducer.createTelegramProductPriceDto(id);
    }

}