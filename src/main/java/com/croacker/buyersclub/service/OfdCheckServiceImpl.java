package com.croacker.buyersclub.service;

import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.domain.Product;
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
        var dto = new AddOrganizationDto().setName(ofdCheck.getUser()).setInn(ofdCheck.getUserInn());
        return organizationService.save(dto);
    }

    private ShopDto saveShop(OfdCheck ofdCheck) {
        var dto = new AddShopDto().setName(ofdCheck.getUser()).setAddress(ofdCheck.getRetailPlaceAddress());
        return shopService.save(dto);
    }

    private CashierDto saveCashier(OfdCheck ofdCheck) {
        var dto = new AddCashierDto().setName(ofdCheck.getOperator());
        return cashierService.save(dto);
    }

    private List<ProductDto> saveProducts(OfdCheck ofdCheck) {
        return ofdCheck.getItems().stream().map(item -> {
            var dto = new AddProductDto().setName(item.getName());
            return productService.save(dto);
        }).collect(Collectors.toList());
    }

}
