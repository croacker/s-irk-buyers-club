package com.croacker.buyersclub.service.mapper.shop;


import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AddDtoToShopTest {

    @Autowired
    private AddDtoToShop mapper;

    @Test
    void shouldMapDto() {
        //given
        var given = createDto();
        var expected = createEntity();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Shop createEntity() {
        return new Shop()
                .setName("test_shop")
                .setAddress("test_address");
    }

    private AddShopDto createDto() {
        return new AddShopDto()
                .setName("test_shop")
                .setAddress("test_address");
    }

}
