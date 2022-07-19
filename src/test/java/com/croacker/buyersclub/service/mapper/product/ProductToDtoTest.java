package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductToDtoTest {

    @Autowired
    private ProductToDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

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
        var productGroup = new ProductGroup().setId(0L);
        return new Product()
                .setId(0L)
                .setName("test_product")
                .setProductGroup(productGroup)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private ProductDto createDto() {
        return new ProductDto()
                .setId(0L)
                .setName("test_product")
                .setProductGroupId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }


}