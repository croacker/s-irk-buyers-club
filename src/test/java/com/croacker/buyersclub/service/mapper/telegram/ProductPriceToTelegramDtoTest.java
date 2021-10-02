package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.ProductPriceView;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.format.NumberService;
import com.croacker.buyersclub.service.format.NumberServiceImpl;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductPriceToTelegramDtoTest {

    private NumberService numberService;

    private ProductPriceToTelegramDto mapper;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup() {
        numberService = new NumberServiceImpl();
        mapper = new ProductPriceToTelegramDto(numberService);
    }

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