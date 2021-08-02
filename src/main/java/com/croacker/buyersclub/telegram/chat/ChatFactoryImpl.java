package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramOrganizationDtoToString;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import com.croacker.buyersclub.service.mapper.telegram.TelegramShopDtoToString;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@Deprecated
public class ChatFactoryImpl implements ChatFactory {

    private final ProductPriceService productPriceService;

    private final OrganizationService organizationService;

    private final ShopService shopService;

    private final TelegramShopDtoToString shopToStringMapper;

    private final TelegramOrganizationDtoToString organizationToStringMapper;

    private final TelegramProductPriceDtoToString productPriceToStringMapper;

    @Override
    public Chat createChat(String id, ChatType type) {
        return switch (type){
            case SHOP -> createShopChat(id);
            case ORGANIZATION -> createOrganizationChat(id);
            default -> createProductChat(id);
        };
    }

    /**
     * Чат поиска товаров.
     * @param id идентификатор чата
     * @return
     */
    private Chat createProductChat(String id) {
        return new ProductChat(id, productPriceService, productPriceToStringMapper);
    }

    /**
     * Чат поиска магазинов.
     * @param id идентификатор чата
     * @return
     */
    private Chat createShopChat(String id) {
        return new ShopChat(id, shopService, shopToStringMapper);
    }

    /**
     * Чат поиска организаций.
     * @param id идентификатор чата
     * @return
     */
    private Chat createOrganizationChat(String id) {
        return new OrganizationChat(id, organizationService, organizationToStringMapper);
    }

}
