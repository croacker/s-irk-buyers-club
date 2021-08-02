package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.mapper.checkline.CashCheckLineToInfoDto;
import com.croacker.buyersclub.service.mapper.telegramuser.TelegramUserToDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class CashCheckToInfoDtoTest {

    private final static LocalDateTime NOW = LocalDateTime.now();

    private CashCheckLineToInfoDto lineMapper;

    private TelegramUserToDto telegramUserToDto;

    private CashCheckToInfoDto mapper;

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
        var cashier = new Cashier().setId(1L).setName("test_cashier");
        return new CashCheck()
                .setId(0L)
                .setCashier(cashier)
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setCheckLines(Collections.emptyList())
                .setDeleted(false);
    }

    private CashCheckInfoDto createDto() {
        return new CashCheckInfoDto()
                .setId(0L)
                .setCashierId(1L)
                .setCashierName("test_cashier")
                .setCheckLines(Collections.emptyList())
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

}

