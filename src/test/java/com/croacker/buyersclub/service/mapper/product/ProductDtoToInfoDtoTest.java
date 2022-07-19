package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.product.ProductInfoDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductDtoToInfoDtoTest {

    @Autowired
    private ProductDtoToInfoDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @Test
    void shouldMapDto() {
        //given
        var given = createEntity();
        var expected = createInfoDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Product createEntity() {
        var productGroup = new ProductGroup().setId(0L).setName("test_product_group");
        return new Product()
                .setId(0L)
                .setName("test_product")
                .setProductGroup(productGroup)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private ProductInfoDto createInfoDto() {
        return new ProductInfoDto()
                .setId(0L)
                .setName("test_product")
                .setProductGroupId(0L)
                .setProductGroupName("test_product_group")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

}