package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.format.DateTimeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DateTimeServiceTest {

    private DateTimeService service;

    @BeforeEach
    void setup() {
        service = new DateTimeServiceImpl();
    }

    @Test
    void dateTimeToEpoch() {
        //given
        var given = LocalDateTime.of(2020, 11, 22, 23, 34, 41);
        var expected = 1606059281;

        // when
        var actual = service.dateTimeToEpoch(given);

        // then
        assertEquals(actual, expected,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);

    }

    @Test
    void fromEpoch() {
        //given
        var given = 1606059281;
        var expected = LocalDateTime.of(2020, 11, 22, 23, 34, 41);

        // when
        var actual = service.fromEpoch(given);

        // then
        assertEquals(actual, expected,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);

    }

    @Test
    void stringToLocalDateTime() {
        //given
        var given = "2020-11-22T23:34:41";
        var expected = LocalDateTime.of(2020, 11, 22, 23, 34, 41);

        // when
        var actual = service.stringToLocalDateTime(given);

        // then
        assertEquals(actual, expected,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void localDateTimeToString() {
        //given
        var given = LocalDateTime.of(2020, 11, 22, 23, 34, 41);
        var expected = "22-11-2020 23:34:41";
        // when
        var actual = service.localDateTimeToString(given);

        // then
        assertEquals(actual, expected,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }
}