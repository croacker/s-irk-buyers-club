package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineInfoDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CashCheckLineToInfoDtoTest {

    @Autowired
    private CashCheckLineToInfoDto mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

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
        return testEntitiesProducer.createCashCheckLine(0L);
    }

    private CashCheckLineInfoDto createDto() {
        return testEntitiesProducer.createCashCheckLineInfoDto(0L);
    }

}