package com.croacker.buyersclub.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TelegramBotApiConfiguration.class})
class TelegramBotApiConfigurationTest {

    @Autowired
    private TelegramBotApiConfiguration configuration;

    @Test
    void shouldReturnTelegramBotsApi() {
        // given when
        var telegramBotsApi = configuration.telegramBotsApi();
        // then
        assertNotNull(telegramBotsApi);
    }

}