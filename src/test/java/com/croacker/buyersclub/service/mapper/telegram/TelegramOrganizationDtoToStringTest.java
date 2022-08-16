package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TelegramOrganizationDtoToStringTest {

    @Autowired
    private TelegramOrganizationDtoToString mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @Test
    void shouldMap(){
        //given
        var given = createDto();
        var expected = createString();

        // when
        var actual = mapper.map(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private String createString() {
        return "[test_organization_0, test_inn_0]";
    }

    private OrganizationDto createDto() {
        return testEntitiesProducer.createOrganizationDto(0L);
    }
}