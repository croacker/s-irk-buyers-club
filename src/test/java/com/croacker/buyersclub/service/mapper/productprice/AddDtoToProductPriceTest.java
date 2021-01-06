package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.mapper.productgroup.AddDtoToProductGroupMapper;
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
class AddDtoToProductPriceTest {

    private AddDtoToProductPrice mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        mapper = new AddDtoToProductPrice();
    }

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