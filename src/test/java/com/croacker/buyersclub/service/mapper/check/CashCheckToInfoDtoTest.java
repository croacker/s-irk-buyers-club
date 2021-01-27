package com.croacker.buyersclub.service.mapper.check;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.mapper.checkline.CashCheckLineToInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class CashCheckToInfoDtoTest {

    private CashCheckLineToInfoDto lineMapper;

    private CashCheckToInfoDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setup() {
        lineMapper = new CashCheckLineToInfoDto();
        mapper = new CashCheckToInfoDto(lineMapper);
    }

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity();
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

}

