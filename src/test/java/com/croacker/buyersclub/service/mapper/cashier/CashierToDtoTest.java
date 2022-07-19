package com.croacker.buyersclub.service.mapper.cashier;

import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CashierToDtoTest {

    @Autowired
    private CashierToDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Cashier createEntity() {
        var shop = new Shop()
                .setId(0L)
                .setName("test_shop")
                .setAddress("test_address")
                .setDeleted(false);
        return new Cashier()
                .setId(0L)
                .setName("test_cashier")
                .setShop(shop)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private CashierDto createDto() {
        return new CashierDto()
                .setId(0L)
                .setName("test_cashier")
                .setShopId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }


}