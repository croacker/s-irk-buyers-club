package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FromToAddTelegramUserTest {

    @Autowired
    private FromToAddTelegramUser mapper;

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
        user.setId(0L);
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