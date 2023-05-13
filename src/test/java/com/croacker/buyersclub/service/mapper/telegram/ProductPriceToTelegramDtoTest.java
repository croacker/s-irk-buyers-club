package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.domain.ProductPriceView;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductPriceToTelegramDtoTest {

    @Autowired
    private ProductPriceToTelegramDto mapper;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity(0L);
        var expected = createDto(0L);

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private ProductPriceView createEntity(long id) {
        return testEntitiesProducer.createProductPriceView(id);
    }

    private TelegramProductPriceDto createDto(long id) {
        return testEntitiesProducer.createTelegramProductPriceDto(id);
    }
}