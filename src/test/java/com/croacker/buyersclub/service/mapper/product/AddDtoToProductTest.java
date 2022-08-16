package com.croacker.buyersclub.service.mapper.product;


import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.tests.TestEntitiesProducer;
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

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

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
        return testEntitiesProducer.createProduct(0L)
                .setId(null)
                .setProductGroup(null)
                .setCreatedAt(null)
                .setUpdatedAt(null)
                .setDeleted(null);
    }

    private AddProductDto createDto() {
        return testEntitiesProducer.createAddProductDto(0L);
    }

}
