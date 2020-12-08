package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.checkline.ItemToAddCheckLineDto;
import com.croacker.buyersclub.service.ofd.Item;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfdCheckServiceImpl implements OfdCheckService {

    private final OrganizationService organizationService;

    private final ShopService shopService;

    private final CashierService cashierService;

    private final ProductService productService;

    private final CheckService checkService;

    private final ProductPriceService productPriceService;

    private final ItemToAddCheckLineDto itemToAddCheckLine;

    @Override
    public void process(OfdCheck ofdCheck) {
        var organization = saveOrganization(ofdCheck);
        var shop = saveShop(ofdCheck, organization);
        var cashier = saveCashier(ofdCheck, shop);
        var products = saveProducts(ofdCheck, shop);
        saveCheck(cashier, products, ofdCheck);
    }

    /**
     *  @param cashier
     * @param checkLines
     * @param ofdCheck
     */
    private void saveCheck(CashierDto cashier, List<AddCashCheckLineDto> checkLines, OfdCheck ofdCheck) {
        var dateTime = fromEpoch(ofdCheck.getDateTime());
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
                .setCheckLines(checkLines);
        checkService.save(checkDto);
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
        if (ofdCheck.getRetailPlaceAddress() == null) {
            shop = shopService.findByName(ofdCheck.getUser());
        } else {
            shop = shopService.findByAddress(ofdCheck.getRetailPlaceAddress());
        }
        if (shop == null) {
            var dto = new AddShopDto()
                    .setName(ofdCheck.getUser())
                    .setAddress(ofdCheck.getRetailPlaceAddress())
                    .setOrganizationId(organization.getId());
            shop = shopService.save(dto);
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
        var cashier = cashierService.findByName(ofdCheck.getOperator());
        if (cashier == null) {
            var dto = new AddCashierDto()
                    .setName(ofdCheck.getOperator())
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

    // TODO в common-класс
    private LocalDateTime fromEpoch(int datetime) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(datetime), TimeZone.getDefault().toZoneId());
    }

}
