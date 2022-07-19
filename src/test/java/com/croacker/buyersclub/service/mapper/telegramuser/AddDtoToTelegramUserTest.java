package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.domain.TelegramUser;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AddDtoToTelegramUserTest {

    @Autowired
    private AddDtoToTelegramUser mapper;

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

    private TelegramUser createEntity() {
        return new TelegramUser()
                .setId(0L)
                .setUserName("test_username")
                .setFirstName("test_firstname")
                .setLastName("test_lastname");
    }

    private AddTelegramUserDto createDto() {
        return new AddTelegramUserDto()
                .setId(0L)
                .setUserName("test_username")
                .setFirstName("test_firstname")
                .setLastName("test_lastname");
    }

}