package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.ofd.Item;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfdCheckServiceImpl implements OfdCheckService{

    private final OrganizationService organizationService;

    private final ShopService shopService;

    private final CashierService cashierService;

    private final ProductService productService;

    @Override
    public void process(OfdCheck ofdCheck) {
        var organization = saveOrganization(ofdCheck);
        var shop = saveShop(ofdCheck, organization);
        var cashier = saveCashier(ofdCheck, shop);
        saveProducts(ofdCheck);
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

    private List<ProductDto> saveProducts(OfdCheck ofdCheck) {
        return ofdCheck.getItems().stream().map(item -> {
            var product = productService.findByName(item.getName());
            if(product == null) {
                var dto = new AddProductDto().setName(item.getName());
                product = productService.save(dto);
            }
            savePrice(item, product);
            return product;
        }).collect(Collectors.toList());
    }

    private void savePrice(Item item, ProductDto product) {

    }

}
