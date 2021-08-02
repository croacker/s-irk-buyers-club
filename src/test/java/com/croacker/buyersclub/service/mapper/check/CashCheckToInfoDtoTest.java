package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.mapper.checkline.CashCheckLineToInfoDto;
import com.croacker.buyersclub.service.mapper.telegramuser.TelegramUserToDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class CashCheckToInfoDtoTest {

    private CashCheckLineToInfoDto lineMapper;

    private TelegramUserToDto telegramUserToDto;

    private CashCheckToInfoDto mapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup() {
        lineMapper = new CashCheckLineToInfoDto();
        telegramUserToDto = new TelegramUserToDto();
        mapper = new CashCheckToInfoDto(lineMapper, telegramUserToDto);
    }

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private CashCheck createEntity() {
        return testEntitiesProducer.createCashCheck(0L);
    }

    private CashCheckInfoDto createDto() {
        return testEntitiesProducer.createCashCheckInfoDto(0L);
    }

}

