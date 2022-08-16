package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TelegramShopDtoToStringTest {

    @Autowired
    private TelegramShopDtoToString mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @Test
    void shouldMapEntity() {
        //given
        var given = createDto();
        var expected = createString();

        // when
        var actual = mapper.map(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private String createString() {
        return "[test_shop_0, test_address_0]";
    }

    private ShopDto createDto() {
        return testEntitiesProducer.createShopDto(0L);
    }

}