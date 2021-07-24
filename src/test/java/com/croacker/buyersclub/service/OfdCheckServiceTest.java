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
import com.croacker.tests.TestEntitiesProducer;
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

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

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
        return testEntitiesProducer.createOfdCheck(0L);
    }

    private TelegramFileProcessResult createTelegramFileProcessResult() {
        return new TelegramFileProcessResult()
                .setCheckInfo(localDateTimeToString(NOW) + " test_fiscal_document_number");
    }

    private OrganizationDto createOrganization() {
        return testEntitiesProducer.createOrganizationDto(0L);
    }

    private ShopDto createShop() {
        return testEntitiesProducer.createShopDto(0L);
    }

    private CashierDto createCashier() {
        return testEntitiesProducer.createCashierDto(0L);
    }

    private CashCheckDto createCashCheckDto() {
        return testEntitiesProducer.createCashCheckDto(0L);
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