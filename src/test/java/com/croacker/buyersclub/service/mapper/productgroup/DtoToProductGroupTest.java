package com.croacker.buyersclub.service.mapper.productgroup;


import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DtoToProductGroupTest {

    @Autowired
    private DtoToProductGroup mapper;

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

    private ProductGroup createEntity() {
        return testEntitiesProducer.createProductGroup(0L)
                .setCreatedAt(null)
                .setUpdatedAt(null);
    }

    private ProductGroupDto createDto() {
        return testEntitiesProducer.createProductGroupDto(0L);
    }

}
