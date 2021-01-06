package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class CashCheckToDtoMapperTest {

    private CashCheckToDtoMapper mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        mapper = new CashCheckToDtoMapper();
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
        var cashier = new Cashier().setId(0L);
        return new CashCheck()
                .setCashier(cashier)
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

    private CashCheckDto createDto() {
        return new CashCheckDto()
                .setCashierId(0L)
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