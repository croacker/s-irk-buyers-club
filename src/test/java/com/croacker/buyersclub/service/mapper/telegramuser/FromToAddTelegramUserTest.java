package com.croacker.buyersclub.service.mapper.telegramuser;

import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.tests.TestEntitiesProducer;
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

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

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
        user.setUserName("test_user_name_0");
        user.setFirstName("test_first_name_0");
        user.setLastName("test_last_name_0");
        return user;
    }

    private AddTelegramUserDto createDto() {
        return testEntitiesProducer.createAddTelegramUserDto(0L);
    }

}