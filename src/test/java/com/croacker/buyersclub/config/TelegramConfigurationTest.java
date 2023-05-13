package com.croacker.buyersclub.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TelegramConfigurationTest {

    @Autowired
    private TelegramConfiguration configuration;

    @Test
    void shouldReturnParameters() {
        assertEquals("telegramToken", configuration.getToken());
        assertEquals("telegramUsername", configuration.getUsername());
    }

}