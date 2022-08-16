package com.croacker.buyersclub.service.mapper.shop;


import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AddDtoToShopTest {

    @Autowired
    private AddDtoToShop mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

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
        return testEntitiesProducer.createShop(0L)
                .setId(null)
                .setOrganization(null)
                .setCreatedAt(null)
                .setUpdatedAt(null)
                .setDeleted(null);
    }

    private AddShopDto createDto() {
        return testEntitiesProducer.createAddShopDto(0L);
    }

}
