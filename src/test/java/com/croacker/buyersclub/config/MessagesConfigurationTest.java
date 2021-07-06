package com.croacker.buyersclub.config;

import com.croacker.buyersclub.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MessagesConfiguration.class})
class MessagesConfigurationTest {

    @Autowired
    private MessagesConfiguration configuration;

    @Test
    void shouldReturnMessageSource(){
        // given when
        var messageSource = configuration.messageSource();
        // then
        assertNotNull(messageSource);
    }

}