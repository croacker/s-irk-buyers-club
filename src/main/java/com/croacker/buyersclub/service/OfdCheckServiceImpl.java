package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
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
        saveOrganization(ofdCheck);
        saveShop(ofdCheck);
        saveCashier(ofdCheck);
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

    private ShopDto saveShop(OfdCheck ofdCheck) {
        ShopDto shop = null;
        if(ofdCheck.getRetailPlaceAddress() == null){
            shop = shopService.findByName(ofdCheck.getUser());
        }else{
            shop = shopService.findByAddress(ofdCheck.getRetailPlaceAddress());
        }
        if (shop == null) {
            var dto = new AddShopDto().setName(ofdCheck.getUser()).setAddress(ofdCheck.getRetailPlaceAddress());
            shop = shopService.save(dto);
        }
        return shop;
    }

    private CashierDto saveCashier(OfdCheck ofdCheck) {
        var cashier = cashierService.findByName(ofdCheck.getOperator());
        if(cashier == null){
            var dto = new AddCashierDto().setName(ofdCheck.getOperator());
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
            return product;
        }).collect(Collectors.toList());
    }

}
