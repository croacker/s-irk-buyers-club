package com.croacker.buyersclub.service.mapper.productgroup;

import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductGroupToDtoTest {

    @Autowired
    private ProductGroupToDto mapper;

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

    private ProductGroup createEntity() {
        return new ProductGroup()
                .setId(0L)
                .setName("test_product_group")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private ProductGroupDto createDto() {
        return new ProductGroupDto()
                .setId(0L)
                .setName("test_product_group")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }


}