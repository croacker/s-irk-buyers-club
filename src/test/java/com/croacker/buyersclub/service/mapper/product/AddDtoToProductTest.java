package com.croacker.buyersclub.service.mapper.product;


import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AddDtoToProductTest {

    @Autowired
    private AddDtoToProduct mapper;

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

    private Product createEntity() {
        return new Product()
                .setName("test_product");
    }

    private AddProductDto createDto() {
        return new AddProductDto()
                .setName("test_product");
    }

}
