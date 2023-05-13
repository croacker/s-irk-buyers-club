package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import com.croacker.buyersclub.service.ofd.Item;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ItemToAddCheckLineDtoTest {

    @Autowired
    private ItemToAddCheckLineDto mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @Test
    void shouldMapDto() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Item createEntity() {
        return testEntitiesProducer.createItem();
    }

    private AddCashCheckLineDto createDto() {
        return testEntitiesProducer.createAddCashCheckLineDto()
                .setProductId(null)
                .setQuantity(2000);
    }

}
