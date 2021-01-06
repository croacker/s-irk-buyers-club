package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductPriceToDtoTest {

    private ProductPriceToDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        mapper = new ProductPriceToDto();
    }

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

    private ProductPrice createEntity() {
        var shop = new Shop().setId(1L);
        var product = new Product().setId(1L);
        return new ProductPrice()
                .setId(1L)
                .setShop(shop)
                .setProduct(product)
                .setPrice(1)
                .setPriceDate(NOW);
    }

    private ProductPriceDto createDto() {
        return new ProductPriceDto()
                .setId(1L)
                .setShopId(1L)
                .setProductId(1L)
                .setPrice(1)
                .setPriceDate(NOW);
    }

}