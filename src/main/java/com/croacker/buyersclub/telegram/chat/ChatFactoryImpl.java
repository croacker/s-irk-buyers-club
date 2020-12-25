package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramOrganizationDtoToString;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ChatFactoryImpl implements ChatFactory {

    private final ProductPriceService productPriceService;

    private final OrganizationService organizationService;

    private final ShopService shopService;

    private final TelegramProductPriceDtoToString productPriceToStringMapper;

    private final TelegramOrganizationDtoToString organizationToStringMapper;

    @Override
    public Chat createChat(Long id, String type) {
        return switch (type){
            case "shop" -> createShopChat(id);
            case "organization" -> createOrganizationChat(id);
            default -> createProductChat(id);
        };
    }

    private Chat createProductChat(Long id) {
        return new ProductChat(id, productPriceService, productPriceToStringMapper);
    }

    private Chat createShopChat(Long id) {
        return new ShopChat(id);
    }

    private Chat createOrganizationChat(Long id) {
        return new OrganizationChat(id, organizationService, organizationToStringMapper);
    }

}
