package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.mapper.checkline.ItemToAddCheckLineDto;
import com.croacker.buyersclub.service.mapper.telegram.CashCheckDtoToTelegramFileProcessResult;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class OfdCheckServiceTest {

    private final static LocalDateTime NOW = LocalDateTime.now();

    private final static String STRING_DATE_TIME = "2020-11-22T23:34:41";

    private OfdCheckService service;

    @MockBean
    private OrganizationService organizationService;

    @MockBean
    private ShopService shopService;

    @MockBean
    private CashierService cashierService;

    @MockBean
    private ProductService productService;

    @MockBean
    private CheckService checkService;

    @MockBean
    private ProductPriceService productPriceService;

    private DateTimeService dateTimeService;

    private ItemToAddCheckLineDto itemToAddCheckLine;

    private CashCheckDtoToTelegramFileProcessResult cashCheckDtoToTelegramFileProcessResultMapper;

    @BeforeEach
    void setup() {
        dateTimeService = new DateTimeServiceImpl();
        itemToAddCheckLine = new ItemToAddCheckLineDto();
        cashCheckDtoToTelegramFileProcessResultMapper = new CashCheckDtoToTelegramFileProcessResult(dateTimeService);
        service = new OfdCheckServiceImpl(organizationService, shopService,
                cashierService, productService, checkService, dateTimeService,
                productPriceService, itemToAddCheckLine, cashCheckDtoToTelegramFileProcessResultMapper);
    }

    @Test
    void shouldProcess(){
        // given
        var given = createOfdCheck();
        var telegramUserId = 1L;
        when(organizationService.findByInn("test_user_inn")).thenReturn(createOrganization());
        when(shopService.findByName("test_shop")).thenReturn(createShop());
        when(cashierService.findByName(any())).thenReturn(createCashier());
        when(checkService.save(any())).thenReturn(createCashCheckDto());

        var expected = createTelegramFileProcessResult();

        // when
        var actual = service.process(given, telegramUserId);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
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

    private TelegramFileProcessResult createTelegramFileProcessResult() {
        return new TelegramFileProcessResult()
                .setCheckInfo(localDateTimeToString(NOW) + " test_fiscal_document_number");
    }

    private OrganizationDto createOrganization() {
        return new OrganizationDto()
                .setId(1L)
                .setName("test_name")
                .setInn("test_user_inn");
    }

    private ShopDto createShop() {
        return new ShopDto()
                .setId(0L)
                .setName("test_shop")
                .setAddress("test_address")
                .setDeleted(false);
    }

    private CashierDto createCashier() {
        return new CashierDto()
                .setId(0L)
                .setName("test_cashier")
                .setShopId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private CashCheckDto createCashCheckDto() {
        return new CashCheckDto()
                .setId(1L)
                .setCashierId(1L)
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(1000)
                .setCashSum(300)
                .setEcashSum(700)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

    private LocalDateTime stringToLocalDateTime(String str){
        return dateTimeService.stringToLocalDateTime(str);
    }

    private int dateTimeToEpoch(LocalDateTime date){
        return dateTimeService.dateTimeToEpoch(date);
    }

    private String localDateTimeToString(LocalDateTime date){
        return dateTimeService.localDateTimeToString(date);
    }
}