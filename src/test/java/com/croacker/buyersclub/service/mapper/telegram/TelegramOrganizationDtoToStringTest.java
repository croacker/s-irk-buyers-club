package com.croacker.buyersclub.service.mapper.telegram;

import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TelegramOrganizationDtoToStringTest {

    @Autowired
    private TelegramOrganizationDtoToString mapper;

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