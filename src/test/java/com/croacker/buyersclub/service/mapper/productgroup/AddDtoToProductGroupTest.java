package com.croacker.buyersclub.service.mapper.productgroup;


import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AddDtoToProductGroupTest {

    @Autowired
    private AddDtoToProductGroup mapper;

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

    private ProductGroup createEntity() {
        return new ProductGroup()
                .setName("test_product_group");
    }

    private AddProductGroupDto createDto() {
        return new AddProductGroupDto()
                .setName("test_product_group");
    }

}
