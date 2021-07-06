package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramOrganizationDtoToString;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import com.croacker.buyersclub.service.mapper.telegram.TelegramShopDtoToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class ChatPoolImplTest {

    @MockBean
    private ChatFactory chatFactory;

    @MockBean
    private ProductPriceService productPriceService;

    @MockBean
    private TelegramProductPriceDtoToString productToStringMapper;

    @MockBean
    private OrganizationService organizationService;

    @MockBean
    private TelegramOrganizationDtoToString organizationToStringMapper;

    @MockBean
    private ShopService shopService;

    @MockBean
    private TelegramShopDtoToString shopToStringMapper;


    private Map<Long, Chat> pool;

    private ChatPool chatPool;

    @BeforeEach
    void setup() {
        pool = new HashMap<>();
        chatPool = new ChatPoolImpl(chatFactory, pool);
    }

    @Test
    void shouldGetProductChat() {
        // given
        var chatId = 0L;
        var expected = new ProductChat(chatId, productPriceService, productToStringMapper);
        when(chatFactory.createChat(chatId, ChatType.PRODUCT)).thenReturn(expected);

        // when
        var actual = chatPool.getChat(chatId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateAndRepeatReturnProductChat() {
        // given
        var chatId = 0L;
        var expected = new ProductChat(chatId, productPriceService, productToStringMapper);
        when(chatFactory.createChat(chatId, ChatType.PRODUCT)).thenReturn(expected);

        // when
        var actual1 = chatPool.getChat(chatId);
        var actual2 = chatPool.getChat(chatId);

        // then
        assertEquals(expected, actual1);
        assertTrue(actual1 == actual2);
    }

    @Test
    void shouldCreateOrganizationChat() {
//        // given
//        var chatId = 0L;
//        var expected = new OrganizationChat(chatId, organizationService, organizationToStringMapper);
//        when(chatFactory.createChat(chatId, ChatType.ORGANIZATION)).thenReturn(expected);
//
//        // when
//        var actual = chatPool.getChat(chatId);
//
//        // then
//        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateAndRepeatReturnOrganizationChat() {
//        // given
//        var chatId = 0L;
//        var expected = new OrganizationChat(chatId, organizationService, organizationToStringMapper);
//        when(chatFactory.createChat(chatId, ChatType.ORGANIZATION)).thenReturn(expected);
//
//        // when
//        var actual1 = chatPool.getChat(chatId, ChatType.ORGANIZATION.toString());
//        var actual2 = chatPool.getChat(chatId, ChatType.ORGANIZATION.toString());
//
//        // then
//        assertEquals(expected, actual1);
//        assertTrue(actual1 == actual2);
    }

    @Test
    void shouldCreateShopChat() {
//        // given
//        var chatId = 0L;
//        var expected = new ShopChat(chatId, shopService, shopToStringMapper);
//        when(chatFactory.createChat(chatId, ChatType.ORGANIZATION)).thenReturn(expected);
//
//        // when
//        var actual = chatPool.getChat(chatId, ChatType.ORGANIZATION.toString());
//
//        // then
//        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateAndRepeatReturnShopChat() {
//        // given
//        var chatId = 0L;
//        var expected = new ShopChat(chatId, shopService, shopToStringMapper);
//        when(chatFactory.createChat(chatId, ChatType.SHOP)).thenReturn(expected);
//
//        // when
//        var actual1 = chatPool.getChat(chatId, ChatType.SHOP.toString());
//        var actual2 = chatPool.getChat(chatId, ChatType.SHOP.toString());
//
//        // then
//        assertEquals(expected, actual1);
//        assertTrue(actual1 == actual2);
    }
}
