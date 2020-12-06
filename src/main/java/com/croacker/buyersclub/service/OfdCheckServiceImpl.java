package com.croacker.buyersclub.service;

import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.product.ProductInfoDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.product.ProductDtoToInfoDto;
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
public class OfdCheckServiceImpl implements OfdCheckService{

    private final OrganizationService organizationService;

    private final ShopService shopService;

    private final CashierService cashierService;

    private final ProductService productService;

    private final ProductPriceService productPriceService;

    private final ProductDtoToInfoDto toInfoDto;

    @Override
    public void process(OfdCheck ofdCheck) {
        var organization = saveOrganization(ofdCheck);
        var shop = saveShop(ofdCheck, organization);
        saveCashier(ofdCheck, shop);
        saveProducts(ofdCheck, shop);
    }

    private OrganizationDto saveOrganization(OfdCheck ofdCheck){
        var organization = organizationService.findByInn(ofdCheck.getUserInn());
        if(organization == null) {
            var dto = new AddOrganizationDto().setName(ofdCheck.getUser()).setInn(ofdCheck.getUserInn());
            organization = organizationService.save(dto);
        }
        return organization;
    }

    private ShopDto saveShop(OfdCheck ofdCheck, OrganizationDto organization) {
        ShopDto shop;
        if(ofdCheck.getRetailPlaceAddress() == null){
            shop = shopService.findByName(ofdCheck.getUser());
        }else{
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

    private CashierDto saveCashier(OfdCheck ofdCheck, ShopDto shop) {
        var cashier = cashierService.findByName(ofdCheck.getOperator());
        if(cashier == null){
            var dto = new AddCashierDto()
                    .setName(ofdCheck.getOperator())
                    .setShopId(shop.getId());
            cashier = cashierService.save(dto);
        }
        return cashier;
    }

    private List<ProductInfoDto> saveProducts(OfdCheck ofdCheck, ShopDto shop) {
        var dateTime = fromEpoch(ofdCheck.getDateTime());
        return ofdCheck.getItems().stream().map(item -> {
            var product = productService.findByName(item.getName());
            if(product == null) {
                var dto = new AddProductDto().setName(item.getName());
                product = productService.save(dto);
            }
            var price = savePrice(shop, product, item, dateTime);
            return toInfoDto.map(product).setPrice(price);
        }).collect(Collectors.toList());
    }

    private ProductPriceDto savePrice(ShopDto shop, ProductDto product, Item item, LocalDateTime dateTime) {
        var price = productPriceService.findPrice(product, shop, dateTime);
        if(price == null){
            var dto = new AddProductPriceDto()
                    .setProductId(product.getId())
                    .setShopId(shop.getId())
                    .setPrice(item.getPrice());
            price = productPriceService.save(dto);
        }
        return price;
    }

    // TODO в common-класс
    private LocalDateTime fromEpoch(int datetime){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(datetime), TimeZone.getDefault().toZoneId());
    }

}
