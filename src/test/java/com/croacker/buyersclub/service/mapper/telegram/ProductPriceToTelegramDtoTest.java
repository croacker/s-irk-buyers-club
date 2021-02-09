package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.Shop;
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
class ProductPriceToTelegramDtoTest {

    private ProductPriceToTelegramDto mapper;

    @BeforeEach
    void setup() {
        mapper = new ProductPriceToTelegramDto();
    }

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private ProductPrice createEntity() {
        var product = new Product().setName("test_product").setId(0L);
        var shop = new Shop().setName("test_shop");
        return new ProductPrice()
                .setId(0L)
                .setShop(shop)
                .setProduct(product)
                .setPrice(15017);
    }

    private TelegramProductPriceDto createDto() {
        return new TelegramProductPriceDto()
                .setProductId(0L)
                .setName("test_product")
                .setPrice("150.17");
    }
}