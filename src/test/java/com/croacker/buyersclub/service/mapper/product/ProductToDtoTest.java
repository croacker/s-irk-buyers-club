package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductToDtoTest {

    private ProductToDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        mapper = new ProductToDto();
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

    private Product createEntity() {
        return new Product()
                .setId(0L)
                .setName("test_product")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private ProductDto createDto() {
        return new ProductDto()
                .setId(0L)
                .setName("test_product")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }


}