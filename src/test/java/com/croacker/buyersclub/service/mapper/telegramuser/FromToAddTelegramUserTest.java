package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class FromToAddTelegramUserTest {

    private FromToAddTelegramUser mapper;

    @BeforeEach
    void setup() {
        mapper = new FromToAddTelegramUser();
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

    private User createEntity() {
        var user = new User();
        user.setId(0);
        user.setUserName("test_username");
        user.setFirstName("test_firstname");
        user.setLastName("test_lastname");
        return user;
    }

    private AddTelegramUserDto createDto() {
        return new AddTelegramUserDto()
                .setId(0L)
                .setUserName("test_username")
                .setFirstName("test_firstname")
                .setLastName("test_lastname");
    }

}