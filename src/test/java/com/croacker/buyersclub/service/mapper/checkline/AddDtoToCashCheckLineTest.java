package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class AddDtoToCashCheckLineTest {

    private AddDtoToCashCheckLine mapper;

    @BeforeEach
    void setup() {
        mapper = new AddDtoToCashCheckLine();
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