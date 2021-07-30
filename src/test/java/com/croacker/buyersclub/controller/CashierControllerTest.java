package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.CashierService;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.tests.TestEntitiesProducer;
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

@WebFluxTest(CashierController.class)
class CashierControllerTest {

    @Autowired
    WebTestClient client;

    @MockBean
    private CashierService service;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    public static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    public void shouldReturnAllCashiers() {
        // given
        var given1 = testEntitiesProducer.createCashierDto(0L);//new CashierDto().setId(1L).setName("test_cashier_1").setShopId(1L);
        var given2 = testEntitiesProducer.createCashierDto(1L);//new CashierDto().setId(2L).setName("test_cashier_2").setShopId(1L);
        when(service.findAll(any())).thenReturn(Arrays.asList(given1, given2));

        // when and then
        client.get()
                .uri("/api/v1/cashier")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(cashiers -> cashiers.size(), equalTo(2));
    }

    @Test
    public void shouldReturnCashier() {
        // given
        var expected = testEntitiesProducer.createCashierDto(0L);
        when(service.findOne(0L)).thenReturn(expected);

        // when and then
        client.get()
                .uri("/api/v1/cashier/0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    @Test
    public void shouldCreateCashier() {
        // given
        var given = testEntitiesProducer.createAddCashierDto(0L);
        var expected = testEntitiesProducer.createCashierDto(0L);
        when(service.save(given)).thenReturn(expected);

        // when and then
        client.post()
                .uri("/api/v1/cashier")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    @Test
    public void shouldUpdateCashier() {
        // given
        var given = testEntitiesProducer.createCashierDto(0L);
        var expected = testEntitiesProducer.createCashierDto(0L);
        when(service.update(given)).thenReturn(expected);

        // when and then
        client.put()
                .uri("/api/v1/cashier")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    @Test
    public void shouldDeleteCashier() {
        // given
        var expected = testEntitiesProducer.createCashierDto(0L);
        when(service.delete(0L)).thenReturn(expected);

        // when and then
        client.delete()
                .uri("/api/v1/cashier/0")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

}