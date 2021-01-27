package com.croacker.buyersclub.service.mapper.ofd;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.DateTimeService;
import com.croacker.buyersclub.service.DateTimeServiceImpl;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.excerpt.OfdCheckExcerpt;
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
public class OfdCheckExcerptToOfdCheckTest {

    private final static LocalDateTime NOW = LocalDateTime.now();
    private final static String STRING_DATE_TIME = "2020-11-22T23:34:41";

    private DateTimeService dateTimeService;

    private OfdCheckExcerptToOfdCheck mapper;

    @BeforeEach
    void setUp() {
        dateTimeService = new DateTimeServiceImpl();
        mapper = new OfdCheckExcerptToOfdCheck(dateTimeService);
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
        return new OfdCheckExcerpt()
                .setUser("test_user")
                .setRetailPlaceAddress("test_retail_place_address")
                .setUserInn("test_user_inn")
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setOperator("test_operator")
                .setOperationType(0)
                .setTotalSum(1000)
                .setCashTotalSum(300)
                .setEcashTotalSum(700)
                .setKktRegId("test_kkt_reg_id")
                .setKktNumber("test_kkt_number")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setFiscalSign("test_fiscal_sign")
                .setNdsNo("test_nds_no")
                .setNds0("test_nds_0")
                .setNds10(10)
                .setNdsCalculated10("test_nds_calculated_10")
                .setNds18(18)
                .setNdsCalculated18("test_nds_calculated_18")
                .setTaxationType(0)
                .setItems(Collections.emptyList())
                .setDiscount("test_discount")
                .setDiscountSum("test_discount_sum")
                .setMarkup("test_markup")
                .setMarkupSum("test_markup_sum")
                .setDateTime(STRING_DATE_TIME);
    }

    private OfdCheck createOfdCheck() {
        return new OfdCheck()
                .setUser("test_user")
                .setRetailPlaceAddress("test_retail_place_address")
                .setUserInn("test_user_inn")
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setOperator("test_operator")
                .setOperationType(0)
                .setTotalSum(1000)
                .setCashTotalSum(300)
                .setEcashTotalSum(700)
                .setKktRegId("test_kkt_reg_id")
                .setKktNumber("test_kkt_number")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setFiscalSign("test_fiscal_sign")
                .setNdsNo("test_nds_no")
                .setNds0("test_nds_0")
                .setNds10(10)
                .setNdsCalculated10("test_nds_calculated_10")
                .setNds18(18)
                .setNdsCalculated18("test_nds_calculated_18")
                .setTaxationType(0)
                .setItems(Collections.emptyList())
                .setDiscount("test_discount")
                .setDiscountSum("test_discount_sum")
                .setMarkup("test_markup")
                .setMarkupSum("test_markup_sum")
                .setDateTime(dateTimeToEpoch(stringToLocalDateTime(STRING_DATE_TIME)));
    }

    private LocalDateTime stringToLocalDateTime(String str){
        return dateTimeService.stringToLocalDateTime(str);
    }

    private int dateTimeToEpoch(LocalDateTime date){
        return dateTimeService.dateTimeToEpoch(date);
    }
}