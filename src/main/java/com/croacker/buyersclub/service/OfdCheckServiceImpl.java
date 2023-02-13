package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.format.DateTimeService;
import com.croacker.buyersclub.service.mapper.checkline.ItemToAddCheckLineDto;
import com.croacker.buyersclub.service.ofd.Item;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OfdCheckServiceImpl implements OfdCheckService {

    private static final String UNDEFINED_CASHIER = "Не указан";

    private final OrganizationService organizationService;

    private final ShopService shopService;

    private final CashierService cashierService;

    private final ProductService productService;

    private final CheckService checkService;

    private final DateTimeService dateTimeService;

    private final ProductPriceService productPriceService;

    private final ItemToAddCheckLineDto itemToAddCheckLine;

    @Override
    public CashCheckDto process(OfdCheck ofdCheck, Long telegramUserId) {
        var organization = saveOrganization(ofdCheck);
        var shop = saveShop(ofdCheck, organization);
        var cashier = saveCashier(ofdCheck, shop);
        var products = saveProducts(ofdCheck, shop);
        return saveCheck(cashier, products, ofdCheck, telegramUserId);
    }

    /**
     * @param cashier
     * @param checkLines
     * @param ofdCheck
     * @return
     */
    private CashCheckDto saveCheck(CashierDto cashier, List<AddCashCheckLineDto> checkLines,
                                   OfdCheck ofdCheck, Long telegramUserId) {
        var dateTime = fromEpoch(ofdCheck.getDateTime());
        var check = findCheck(ofdCheck);
        if (check == null) {
            var checkDto = new AddCashCheckDto()// TODO в mapper
                    .setCashierId(cashier.getId())
                    .setRequestNumber(ofdCheck.getRequestNumber())
                    .setShiftNumber(ofdCheck.getShiftNumber())
                    .setKktRegId(ofdCheck.getKktRegId())
                    .setFiscalDocumentNumber(ofdCheck.getFiscalDocumentNumber())
                    .setFiscalDriveNumber(ofdCheck.getFiscalDriveNumber())
                    .setTotalSum(ofdCheck.getTotalSum())
                    .setCashSum(ofdCheck.getCashTotalSum())
                    .setEcashSum(ofdCheck.getEcashTotalSum())
                    .setCheckDate(dateTime)
                    .setCheckLines(checkLines)
                    .setTelegramUserId(telegramUserId);
            check = checkService.save(checkDto);
        }
        return check;
    }

    private CashCheckDto findCheck(OfdCheck ofdCheck) {
        return checkService.findCheck(ofdCheck.getKktRegId(), ofdCheck.getFiscalDriveNumber(),
                ofdCheck.getFiscalDocumentNumber());
    }

    /**
     * Сохранить организацию.
     *
     * @param ofdCheck чек ОФД
     * @return организация
     */
    private OrganizationDto saveOrganization(OfdCheck ofdCheck) {
        var organization = organizationService.findByInn(ofdCheck.getUserInn());
        if (organization == null) {
            var name = ofdCheck.getUser();
            var inn = ofdCheck.getUserInn();
            if (name == null) {
                name = inn;
            }
            var dto = new AddOrganizationDto().setName(name).setInn(inn);
            organization = organizationService.save(dto);
        }
        return organization;
    }

    /**
     * Сохранить магазин.
     *
     * @param ofdCheck     чек ОФД
     * @param organization организация
     * @return магазин
     */
    private ShopDto saveShop(OfdCheck ofdCheck, OrganizationDto organization) {
        ShopDto shop;
        var name = getShopName(ofdCheck, organization);
        if (ofdCheck.getRetailPlaceAddress() != null) {
            shop = shopService.findByAddress(ofdCheck.getRetailPlaceAddress());
        } else {
            shop = shopService.findByName(name);
        }
        if (shop == null) {
            var address = ofdCheck.getRetailPlaceAddress();
            var dto = new AddShopDto()
                    .setName(name)
                    .setAddress(address)
                    .setOrganizationId(organization.getId());
            log.info("Save new Shop: {}", dto);
            shop = shopService.save(dto);
        } else {
            log.info("Shop exists: {}", shop);
        }
        return shop;
    }

    /**
     * Сохранить кассира
     *
     * @param ofdCheck чек ОФД
     * @param shop     магазин
     * @return кассир
     */
    private CashierDto saveCashier(OfdCheck ofdCheck, ShopDto shop) {
        var name = getCashierName(ofdCheck);
        var cashier = cashierService.findByNameAndShopId(name, shop.getId());
        if (cashier == null) {
            var dto = new AddCashierDto()
                    .setName(name)
                    .setShopId(shop.getId());
            cashier = cashierService.save(dto);
        }
        return cashier;
    }

    /**
     * Сохранить товары.
     *
     * @param ofdCheck чек ОФД
     * @param shop     магазин
     * @return товар
     */
    private List<AddCashCheckLineDto> saveProducts(OfdCheck ofdCheck, ShopDto shop) {
        var dateTime = fromEpoch(ofdCheck.getDateTime());
        return ofdCheck.getItems().stream().map(item -> {
            var product = productService.findByName(item.getName());
            if (product == null) {
                var dto = new AddProductDto().setName(item.getName());
                product = productService.save(dto);
            }
            savePrice(shop, product, item, dateTime);
            return itemToAddCheckLine.map(item).setProductId(product.getId());
        }).collect(Collectors.toList());
    }

    /**
     * Сохранить цену.
     * TODO самый уродский метод
     *
     * @param shop     магазин
     * @param product  товар
     * @param item     строка
     * @param dateTime дата-время
     * @return цена
     */
    private ProductPriceDto savePrice(ShopDto shop, ProductDto product, Item item, LocalDateTime dateTime) {
        var price = productPriceService.findPrice(product, shop, dateTime);
        if (price == null) {
            var dto = new AddProductPriceDto()
                    .setProductId(product.getId())
                    .setShopId(shop.getId())
                    .setPrice(item.getPrice())
                    .setPriceDate(dateTime);
            price = productPriceService.save(dto);
        }
        return price;
    }

    private LocalDateTime fromEpoch(int datetime) {
        return dateTimeService.fromEpoch(datetime);
    }

    private String getCashierName(OfdCheck ofdCheck) {
        var name = ofdCheck.getOperator();
        if (StringUtils.isEmpty(name)){
            name = UNDEFINED_CASHIER;
        }
        return name;
    }

    private String getShopName(OfdCheck ofdCheck, OrganizationDto organization){
        var name = ofdCheck.getUser();
        if (name == null) {
            name = organization.getName();
        }
        return name;
    }
}
