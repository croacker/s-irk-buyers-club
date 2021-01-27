package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.TelegramUser;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class TelegramUserToDtoTest {

    private TelegramUserToDto mapper;

    @BeforeEach
    void setup() {
        mapper = new TelegramUserToDto();
    }

    @Test
    void shouldMap() {
        //given
        var given = createEntity();
        var expected = createDto();

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

    private TelegramUserDto createDto() {
        return new TelegramUserDto()
                .setId(0L)
                .setUserName("test_username")
                .setFirstName("test_firstname")
                .setLastName("test_lastname");
    }
}