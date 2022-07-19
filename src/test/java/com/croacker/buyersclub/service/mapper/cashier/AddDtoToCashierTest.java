package com.croacker.buyersclub.service.mapper.cashier;


import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AddDtoToCashierTest {

    @Autowired
    private AddDtoToCashier mapper;

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

    private Cashier createEntity() {
        return new Cashier()
                .setName("test_cashier");
    }

    private AddCashierDto createDto() {
        return new AddCashierDto()
                .setName("test_cashier");
    }

}
