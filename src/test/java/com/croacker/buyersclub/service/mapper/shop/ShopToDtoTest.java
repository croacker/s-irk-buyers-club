package com.croacker.buyersclub.service.mapper.shop;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShopToDtoTest {

    @Autowired
    private ShopToDto mapper;

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

    private Shop createEntity() {
        var organization = new Organization()
                .setId(0L)
                .setName("test_organization")
                .setInn("test_inn")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
        return new Shop()
                .setId(0L)
                .setName("test_shop")
                .setAddress("test_address")
                .setOrganization(organization)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private ShopDto createDto() {
        return new ShopDto()
                .setId(0L)
                .setName("test_shop")
                .setAddress("test_address")
                .setOrganizationId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }


}