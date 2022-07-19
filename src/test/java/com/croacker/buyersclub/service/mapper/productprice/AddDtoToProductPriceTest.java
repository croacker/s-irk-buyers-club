package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AddDtoToProductPriceTest {

    @Autowired
    private AddDtoToProductPrice mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

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

    private ProductPrice createEntity() {
        return new ProductPrice()
                .setPrice(1)
                .setPriceDate(NOW);
    }

    private AddProductPriceDto createDto() {
        return new AddProductPriceDto()
                .setPrice(1)
                .setPriceDate(NOW);
    }

}