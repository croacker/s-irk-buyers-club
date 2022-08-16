package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.ShopService;
import com.croacker.buyersclub.service.dto.shop.AddShopDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ShopController.class)
class ShopControllerTest {

    @Autowired
    WebTestClient client;

    @MockBean
    private ShopService service;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @Test
    public void shouldReturnAllShops() {
        // given
        var given1 = createShopDto(1L);
        var given2 = createShopDto(2L);
        when(service.findAll(any())).thenReturn(Arrays.asList(given1, given2));

        // when and then
        client.get()
                .uri("/api/v1/shop")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(Shops -> Shops.size(), equalTo(2));
    }

    @Test
    public void shouldReturnShop() {
        // given
        var expected = createShopDto(1L);
        when(service.findOne(any())).thenReturn(expected);

        // when and then
        client.get()
                .uri("/api/v1/shop/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ShopDto.class)
                .value(shop -> shop, equalTo(expected));
    }

    @Test
    public void shouldCreateShop() {
        // given
        var given = createAddShopDto(1L);
        var expected = createShopDto(1L);
        when(service.save(given)).thenReturn(expected);

        // when and then
        client.post()
                .uri("/api/v1/shop")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ShopDto.class)
                .value(shop -> shop, equalTo(expected));
    }

    @Test
    public void shouldUpdateShop() {
        // given
        var given = createShopDto(1L);
        var expected = createShopDto(1L);
        when(service.update(given)).thenReturn(expected);

        // when and then
        client.put()
                .uri("/api/v1/shop")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ShopDto.class)
                .value(shop -> shop, equalTo(expected));
    }

    @Test
    public void shouldDeleteShop() {
        // given
        var expected = createShopDto(1L).setDeleted(true);
        when(service.delete(1L)).thenReturn(expected);

        // when and then
        client.delete()
                .uri("/api/v1/shop/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ShopDto.class)
                .value(shop -> shop.getDeleted(), equalTo(true));
    }

    private ShopDto createShopDto(long id) {
        return testEntitiesProducer.createShopDto(id);
    }


    private AddShopDto createAddShopDto(long id) {
        return testEntitiesProducer.createAddShopDto(id);
    }
}