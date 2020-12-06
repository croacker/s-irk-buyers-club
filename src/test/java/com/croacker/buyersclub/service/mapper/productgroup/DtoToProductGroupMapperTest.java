package com.croacker.buyersclub.service.mapper.productgroup;


import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class DtoToProductGroupMapperTest {

    private DtoToProductGroupMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DtoToProductGroupMapper();
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

    private ProductGroup createEntity() {
        return new ProductGroup()
                .setId(0L)
                .setName("test_product_group")
                .setDeleted(false);
    }

    private ProductGroupDto createDto() {
        return new ProductGroupDto()
                .setId(0L)
                .setName("test_product_group")
                .setDeleted(false);
    }

}
