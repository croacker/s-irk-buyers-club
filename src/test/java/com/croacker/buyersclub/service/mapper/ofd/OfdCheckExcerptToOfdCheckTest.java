package com.croacker.buyersclub.service.mapper.ofd;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.excerpt.OfdCheckExcerpt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.org.apache.commons.lang.NotImplementedException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class OfdCheckExcerptToOfdCheckTest {

    private final static LocalDateTime NOW = LocalDateTime.now();

    private OfdCheckExcerptToOfdCheck mapper;

    @BeforeEach
    void setUp() {
        mapper = new OfdCheckExcerptToOfdCheck();
    }

    @Test
    void shouldMapDto() {

        //given
        var given = createOfdCheckExcerpt();
        var expected = createOfdCheck();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);

    }

    private OfdCheckExcerpt createOfdCheckExcerpt() {
        throw new NotImplementedException();
//        return new OfdCheckExcerpt();
    }

    private OfdCheck createOfdCheck() {
        throw new NotImplementedException();
//        return new OfdCheck();
    }

}