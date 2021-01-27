package com.croacker.buyersclub.service.mapper.product;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.product.ProductInfoDto;
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
class ProductDtoToInfoDtoTest {

    private ProductDtoToInfoDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setup() {
        mapper = new ProductDtoToInfoDto();
    }

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