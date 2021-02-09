package com.croacker.buyersclub.service.mapper.productprice;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductPriceToInfoDtoTest {

    private ProductPriceToInfoDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setup() {
        mapper = new ProductPriceToInfoDto();
    }

    @Test
    void shouldMapDto() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private ProductPrice createEntity() {
        var shop = new Shop().setId(1L).setName("test_shop");
        var product = new Product().setId(1L).setName("test_product");;
        return new ProductPrice()
                .setId(1L)
                .setShop(shop)
                .setProduct(product)
                .setPrice(1)
                .setPriceDate(NOW)
                .setDeleted(true);
    }

    private ProductPriceInfoDto createDto() {
        return new ProductPriceInfoDto()
                .setId(1L)
                .setShopId(1L)
                .setShopName("test_shop")
                .setProductId(1L)
                .setProductName("test_product")
                .setPrice(1)
                .setPriceDate(NOW)
                .setDeleted(true);
    }

}