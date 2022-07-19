package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.shop.ShopDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TelegramShopDtoToStringTest {

    @Autowired
    private TelegramShopDtoToString mapper;

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
        return "[test_name, test_address]";
    }

    private ShopDto createDto() {
        return new ShopDto()
                .setName("test_name")
                .setAddress("test_address");
    }

}