package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AddDtoToCashCheckLineTest {

    @Autowired
    private AddDtoToCashCheckLine mapper;

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

    private CashCheckLine createEntity() {
        return new CashCheckLine()
                .setPrice(100)
                .setQuantity(2)
                .setTotalSum(200);
    }

    private AddCashCheckLineDto createDto() {
        return new AddCashCheckLineDto()
                .setPrice(100)
                .setQuantity(2)
                .setTotalSum(200);
    }

}