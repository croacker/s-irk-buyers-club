package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class TelegramOrganizationDtoToStringTest {

    private TelegramOrganizationDtoToString mapper;

    @BeforeEach
    void setup(){
        mapper = new TelegramOrganizationDtoToString();
    }

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
        return "[test_name, test_inn]";
    }

    private OrganizationDto createDto() {
        return new OrganizationDto()
                .setName("test_name")
                .setInn("test_inn");
    }
}