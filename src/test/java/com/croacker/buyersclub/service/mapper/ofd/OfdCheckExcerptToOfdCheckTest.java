package com.croacker.buyersclub.service.mapper.ofd;

import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.excerpt.OfdCheckExcerpt;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OfdCheckExcerptToOfdCheckTest {

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private OfdCheckExcerptToOfdCheck mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

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
        return testEntitiesProducer.createOfdCheckExcerpt();
    }

    // TODO test data producer
    private OfdCheck createOfdCheck() {
        return testEntitiesProducer.createOfdCheck();
    }

}