package com.croacker.tests;

import com.croacker.buyersclub.domain.*;
import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineInfoDto;
import com.croacker.buyersclub.service.dto.product.ProductInfoDto;
import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.format.DateTimeServiceImpl;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import com.croacker.buyersclub.service.format.NumberService;
import com.croacker.buyersclub.service.format.NumberServiceImpl;
import com.croacker.buyersclub.service.ofd.Item;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.excerpt.OfdCheckExcerpt;

import java.time.LocalDateTime;
import java.util.Collections;

public class TestEntitiesProducer {

    public static final LocalDateTime NOW = LocalDateTime.now();

    private final static String STRING_DATE_TIME = "2020-11-22T23:34:41";

    private final DateTimeService dateTimeService = new DateTimeServiceImpl();

    private final NumberService numberService = new NumberServiceImpl();

    public Cashier createCashier(long id){
        return new Cashier()
                .setId(id)
                .setName("test_cashier_" + id)
                .setShop(createShop(0L))
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public CashierDto createCashierDto(long id){
        return new CashierDto()
                .setId(id)
                .setName("test_cashier_" + id)
                .setShopId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public AddCashierDto createAddCashierDto(long id){
        return new AddCashierDto()
                .setName("test_cashier_" + id)
                .setShopId(0L);
    }

    public Shop createShop(long id) {
        return new Shop()
                .setId(id)
                .setName("test_shop_" + id)
                .setAddress("test_address_" + id)
                .setOrganization(createOrganization(0L))
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public ShopDto createShopDto(long id) {
        return new ShopDto()
                .setId(id)
                .setName("test_shop_" + id)
                .setAddress("test_address_" + id)
                .setOrganizationId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public AddShopDto createAddShopDto(long id) {
        return new AddShopDto()
                .setName("test_shop_" + id)
                .setAddress("test_address_" + id)
                .setOrganizationId(0L);
    }

    public Organization createOrganization(long id) {
        return new Organization()
                .setId(id)
                .setName("test_organization_" + id)
                .setInn("test_inn_" + id)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public OrganizationDto createOrganizationDto(long id) {
        return new OrganizationDto()
                .setId(id)
                .setName("test_organization_" + id)
                .setInn("test_inn_" + id)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public AddOrganizationDto createAddOrganizationDto(long id) {
        return new AddOrganizationDto()
                .setName("test_organization_" + id)
                .setInn("test_inn_" + id);
    }

    public CashCheck createCashCheck(long id) {
        return new CashCheck()
                .setId(id)
                .setCashier(createCashier(0L))
                .setRequestNumber("test_request_number_" + id)
                .setShiftNumber("test_shift_number_" + id)
                .setKktRegId("test_kkt_reg_id_" + id)
                .setFiscalDriveNumber("test_fiscal_drive_number_" + id)
                .setFiscalDocumentNumber("test_fiscal_document_number_" + id)
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setCheckLines(Collections.emptyList())
                .setTelegramUser(createTelegramUser(0L))
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public CashCheckDto createCashCheckDto(long id){
        return new CashCheckDto()
                .setId(id)
                .setCashierId(0L)
                .setRequestNumber("test_request_number_" + id)
                .setShiftNumber("test_shift_number_" + id)
                .setKktRegId("test_kkt_reg_id_" + id)
                .setFiscalDriveNumber("test_fiscal_drive_number_" + id)
                .setFiscalDocumentNumber("test_fiscal_document_number_" + id)
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setTelegramUserId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public CashCheckInfoDto createCashCheckInfoDto(long id) {
        return new CashCheckInfoDto()
                .setId(id)
                .setCashierId(0L)
                .setCashierName("test_cashier_" + 0L)
                .setRequestNumber("test_request_number_" + id)
                .setShiftNumber("test_shift_number_" + id)
                .setKktRegId("test_kkt_reg_id_" + id)
                .setFiscalDriveNumber("test_fiscal_drive_number_" + id)
                .setFiscalDocumentNumber("test_fiscal_document_number_" + id)
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setCheckLines(Collections.emptyList())
                .setTelegramUser(createTelegramUserDto(0L))
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public AddCashCheckDto createAddCashCheckDto(long id) {
        return new AddCashCheckDto()
                .setCashierId(0L)
                .setRequestNumber("test_request_number_" + id)
                .setShiftNumber("test_shift_number_" + id)
                .setKktRegId("test_kkt_reg_id_" + id)
                .setFiscalDriveNumber("test_fiscal_drive_number_" + id)
                .setFiscalDocumentNumber("test_fiscal_document_number_" + id)
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setCheckLines(Collections.emptyList())
                .setTelegramUserId(1L);
    }

    public CashCheckLine createCashCheckLine(long id){
        return new CashCheckLine()
                .setId(id)
                .setProduct(createProduct(0L))
                .setPrice(100)
                .setQuantity(2)
                .setTotalSum(200);
    }

    public CashCheckLineInfoDto createCashCheckLineInfoDto(long id){
        return new CashCheckLineInfoDto()
                .setId(id)
                .setProductId(0L)
                .setProductName("test_product_0")
                .setPrice(100)
                .setQuantity(2)
                .setTotalSum(200);
    }

    public AddCashCheckLineDto createAddCashCheckLineDto(){
        return new AddCashCheckLineDto()
                .setProductId(0L)
                .setPrice(100)
                .setQuantity(2)
                .setTotalSum(200);
    }

    public OfdCheck createOfdCheck() {
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

    public Item createItem(){
        return new Item()
                .setPrice(100)
                .setQuantity(2)
                .setSum(200);
    }

    public OfdCheckExcerpt createOfdCheckExcerpt(){
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

    public ProductGroup createProductGroup(long id) {
        return new ProductGroup()
                .setId(id)
                .setName("test_product_group_" + id)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public ProductGroupDto createProductGroupDto(long id) {
        return new ProductGroupDto()
                .setId(id)
                .setName("test_product_group_" + id)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public AddProductGroupDto createAddProductGroupDto(long id) {
        return new AddProductGroupDto()
                .setName("test_product_group_" + id);
    }

    public Product createProduct(long id) {
        return new Product()
                .setId(id)
                .setProductGroup(createProductGroup(0L))
                .setName("test_product_" + id)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public ProductDto createProductDto(long id) {
        return new ProductDto()
                .setId(id)
                .setProductGroupId(0L)
                .setName("test_product_" + id)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public ProductInfoDto createProductInfoDto(long id) {
        return new ProductInfoDto()
                .setId(0L)
                .setName("test_product_" + id)
                .setProductGroupId(0L)
                .setProductGroupName("test_product_group_0")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    public AddProductDto createAddProductDto(long id) {
        return new AddProductDto()
                .setProductGroupId(0L)
                .setName("test_product_" + id);
    }

    public ProductPrice createProductPrice(long id) {
        return new ProductPrice()
                .setId(id)
                .setShop(createShop(0L))
                .setProduct(createProduct(0L))
                .setPrice((int) id)
                .setPriceDate(NOW)
                .setDeleted(false);
    }

    public ProductPriceDto createProductPriceDto(long id) {
        return new ProductPriceDto()
                .setId(id)
                .setShopId(0L)
                .setProductId(0L)
                .setPrice((int) id)
                .setPriceDate(NOW)
                .setDeleted(false);
    }

    public AddProductPriceDto createAddProductPriceDto(long id) {
        return new AddProductPriceDto()
                .setShopId(0L)
                .setProductId(0L)
                .setPrice((int) id)
                .setPriceDate(NOW);
    }

    public ProductPriceInfoDto createProductPriceInfoDto(long id) {
        return new ProductPriceInfoDto()
                .setId(id)
                .setShopId(0L)
                .setShopName("test_shop_" + 0L)
                .setProductId(0L)
                .setProductName("test_product_" + 0L)
                .setPrice((int) id)
                .setPriceDate(NOW)
                .setDeleted(false);
    }

    public ProductPriceView createProductPriceView(long id) {
        return new ProductPriceView()
                .setId(id)
                .setProductId(0L)
                .setProductName("test_product_" + 0L)
                .setShopId(0L)
                .setShopName("test_shop_" + 0L)
                .setPrice((int) id)
                .setPriceDate(NOW);
    }

    public TelegramFileProcessResult createTelegramFileProcessResult(long id) {
        return new TelegramFileProcessResult()
                .setCheckInfo(dateTimeService.localDateTimeToString(NOW) + " test_fiscal_document_number_" + id);
    }

    private LocalDateTime stringToLocalDateTime(String str){
        return dateTimeService.stringToLocalDateTime(str);
    }

    private int dateTimeToEpoch(LocalDateTime date){
        return dateTimeService.dateTimeToEpoch(date);
    }

    public TelegramUser createTelegramUser(long id) {
        return new TelegramUser()
                .setId(id)
                .setUserName("test_user_name_" + id)
                .setFirstName("test_first_name_" + id)
                .setLastName("test_last_name_" + id);
    }

    public TelegramUserDto createTelegramUserDto(long id) {
        return new TelegramUserDto()
                .setId(id)
                .setUserName("test_user_name_" + id)
                .setFirstName("test_first_name_" + id)
                .setLastName("test_last_name_" + id);
    }

    public AddTelegramUserDto createAddTelegramUserDto(long id) {
        return new AddTelegramUserDto()
                .setId(id)
                .setUserName("test_user_name_" + id)
                .setFirstName("test_first_name_" + id)
                .setLastName("test_last_name_" + id);
    }

    public TelegramProductPriceDto createTelegramProductPriceDto(long id) {
        return new TelegramProductPriceDto()
                .setPriceId(id)
                .setProductId(0L)
                .setProductName("test_product_" + id)
                .setShopId(0L)
                .setShopName("test_shop_" + 0L)
                .setPrice(priceToString(id * 100));
    }

    private String priceToString(Long val){
        return numberService.toCurrency(val);
    }

}
