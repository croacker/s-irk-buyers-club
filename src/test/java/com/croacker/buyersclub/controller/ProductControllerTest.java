package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.ProductService;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class ProductControllerTest {

    private final static LocalDateTime NOW = LocalDateTime.now();

    @Autowired
    WebTestClient client;

    @MockBean
    private ProductService service;

    @MockBean
    private ProductPriceService productPriceService;

    @Test
    public void shouldReturnAllProducts() {
        // given
        var given1 = createProductDto(1L);
        var given2 = createProductDto(2L);
        when(service.findAll(any())).thenReturn(Arrays.asList(given1, given2));

        // when and then
        client.get()
                .uri("/api/v1/product")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(product -> product.size(), equalTo(2));
    }

    @Test
    public void shouldReturnProduct() {
        // given
        var expected = createProductDto(1L);
        when(service.findOne(any())).thenReturn(expected);

        // when and then
        client.get()
                .uri("/api/v1/product/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .value(product -> product, equalTo(expected));
    }

    @Test
    public void shouldCreateProduct() {
        // given
        var given = createAddProductDto(1L);
        var expected = createProductDto(1L);
        when(service.save(given)).thenReturn(expected);

        // when and then
        client.post()
                .uri("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .value(product -> product, equalTo(expected));
    }

    @Test
    public void shouldUpdateProduct() {
        // given
        var given = createProductDto(1L);
        var expected = createProductDto(1L);
        when(service.update(given)).thenReturn(expected);

        // when and then
        client.put()
                .uri("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .value(product -> product, equalTo(expected));
    }

    @Test
    public void shouldDeleteProduct() {
        // given
        var expected = createProductDto(1L).setDeleted(true);
        when(service.delete(1L)).thenReturn(expected);

        // when and then
        client.delete()
                .uri("/api/v1/product/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .value(product -> product, equalTo(expected));
    }

    @Test
    public void shouldGetProductPrices() {
        // given
        var expected = createProductPriceInfoDto(1L);
        when(productPriceService.findByProduct(1L)).thenReturn(Arrays.asList(expected));

        // when and then
        client.get()
                .uri("/api/v1/product/1/prices")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(product -> product.size(), equalTo(1));
    }

    private ProductDto createProductDto(long id) {
        return new ProductDto()
                .setId(id)
                .setName("test_product_" + id)
                .setProductGroupId(id)
                .setDeleted(false);
    }

    private AddProductDto createAddProductDto(long id) {
        return new AddProductDto()
                .setName("test_product_" + id)
                .setProductGroupId(id);
    }

    private ProductPriceInfoDto createProductPriceInfoDto(long id) {
        return new ProductPriceInfoDto()
                .setId(id)
                .setShopId(1L)
                .setShopName("test_shop_name")
                .setProductId(id)
                .setProductName("test_product_" + id)
                .setPrice(100)
                .setPriceDate(NOW)
                .setDeleted(false);
    }
}