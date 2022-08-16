package com.croacker.buyersclub.service.mapper.cashier;


import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DtoToCashierTest {

    @Autowired
    private DtoToCashier mapper;

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

    private Cashier createEntity() {
        return testEntitiesProducer.createCashier(0L)
                .setShop(null);
    }

    private CashierDto createDto() {
        return testEntitiesProducer.createCashierDto(0L);
    }

}
