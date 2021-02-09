package com.croacker.buyersclub.service.locale;

import com.croacker.buyersclub.TestConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class LocaleServiceImplTest {

    @MockBean
    private MessageSource messageSource;

    private LocaleServiceImpl service;

    @BeforeEach
    void setup(){
        service = new LocaleServiceImpl(messageSource);
    }

    @Test
    void shouldReturnMessage(){
        // given
        var given = "test_string";
        var expected = "test_message";
        when(messageSource.getMessage(any(), any(), any())).thenReturn(expected);

        // when
        var actual = service.getString(given, "ru_RU");
        // then
        assertEquals(expected, actual);
    }

}