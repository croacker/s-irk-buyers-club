package com.croacker.buyersclub.service.locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
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