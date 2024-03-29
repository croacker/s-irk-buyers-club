package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.format.DateTimeServiceImpl;
import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.locale.LocaleServiceImpl;
import com.croacker.buyersclub.service.mapper.checkline.ItemToAddCheckLineDto;
import com.croacker.buyersclub.service.mapper.telegram.CashCheckDtoToTelegramFileProcessResult;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OfdCheckServiceTest {

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

    @Autowired
    private LocaleService localeService;

    private ItemToAddCheckLineDto itemToAddCheckLine;

    private CashCheckDtoToTelegramFileProcessResult cashCheckDtoToTelegramFileProcessResultMapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup() {
        dateTimeService = new DateTimeServiceImpl();
        itemToAddCheckLine = new ItemToAddCheckLineDto();
        cashCheckDtoToTelegramFileProcessResultMapper = new CashCheckDtoToTelegramFileProcessResult(dateTimeService, localeService);
        service = new OfdCheckServiceImpl(organizationService, shopService,
                cashierService, productService, checkService, dateTimeService,
                productPriceService, itemToAddCheckLine);
    }

    @Test
    void process(){
        // given
        var given = createOfdCheck();
        var telegramUserId = 0L;
        when(organizationService.findByInn("test_user_inn")).thenReturn(createOrganization());
        when(shopService.findByAddress("test_retail_place_address")).thenReturn(createShop());
        when(cashierService.findByNameAndShopId(any(), any())).thenReturn(createCashier());
        when(checkService.save(any())).thenReturn(createCashCheckDto());

        var expected = createCashCheckDto();// TODO fix test

        // when
        var actual = service.process(given, telegramUserId);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private OfdCheck createOfdCheck() {
        return testEntitiesProducer.createOfdCheck();
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

}