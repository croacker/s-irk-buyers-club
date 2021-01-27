package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class TelegramShopDtoToStringTest {

    private TelegramShopDtoToString mapper;

    @BeforeEach
    void setup() {
        mapper = new TelegramShopDtoToString();
    }

    @Test
    void shouldMapEntity() {
        //given
        var given = createDto();
        var expected = createString();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
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