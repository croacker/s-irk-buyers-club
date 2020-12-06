package com.croacker.buyersclub.service.mapper.product;


import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.mapper.product.AddDtoToProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class AddDtoToProductMapperTest {

    private AddDtoToProductMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new AddDtoToProductMapper();
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

    private Product createEntity() {
        return new Product()
                .setName("test_product");
    }

    private AddProductDto createDto() {
        return new AddProductDto()
                .setName("test_product");
    }

}
