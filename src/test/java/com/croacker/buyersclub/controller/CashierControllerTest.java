package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.CashierService;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    public void shouldReturnAllCashiers() {
        // given
        var given1 = new CashierDto().setId(1L).setName("test_cashier_1").setShopId(1L);
        var given2 = new CashierDto().setId(2L).setName("test_cashier_2").setShopId(1L);
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
        var expected = new CashierDto().setId(1L).setName("test_cashier").setShopId(1L);
        when(service.findOne(any())).thenReturn(expected);

        // when and then
        client.get()
                .uri("/api/v1/cashier/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    @Test
    public void shouldCreateCashier() {
        // given
        var given = new AddCashierDto().setName("test_cashier").setShopId(1L);
        var expected = new CashierDto().setId(1L).setName("test_cashier").setShopId(1L);
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
        var given = new CashierDto().setId(1L).setName("test_cashier_updated").setShopId(2L);
        var expected = new CashierDto().setId(1L).setName("test_cashier_updated").setShopId(2L);
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
        var expected = new CashierDto().setId(1L).setName("test_cashier_updated").setShopId(2L).setDeleted(true);
        when(service.delete(1L)).thenReturn(expected);

        // when and then
        client.delete()
                .uri("/api/v1/cashier/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

}