package com.croacker.buyersclub.service.mapper.check;


import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DtoToCashCheckTest {

    @Autowired
    private DtoToCashCheck mapper;

    private static final LocalDateTime NOW = LocalDateTime.now();

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

    private CashCheck createEntity() {
        return testEntitiesProducer.createCashCheck(0L);
    }

    private CashCheckDto createDto() {
        return testEntitiesProducer.createCashCheckDto(0L);
    }

}
