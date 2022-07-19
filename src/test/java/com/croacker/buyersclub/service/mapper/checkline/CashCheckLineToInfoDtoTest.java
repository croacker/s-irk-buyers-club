package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineInfoDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CashCheckLineToInfoDtoTest {

    @Autowired
    private CashCheckLineToInfoDto mapper;

    @Test
    void shouldMapDto() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private CashCheckLine createEntity() {
        var product = new Product().setId(1L).setName("test_product_name");
        return new CashCheckLine()
                .setId(0L)
                .setProduct(product)
                .setPrice(100)
                .setQuantity(2)
                .setTotalSum(200);
    }

    private CashCheckLineInfoDto createDto() {
        return new CashCheckLineInfoDto()
                .setId(0L)
                .setProductId(1L)
                .setProductName("test_product_name")
                .setPrice(100)
                .setQuantity(2)
                .setTotalSum(200);
    }

}