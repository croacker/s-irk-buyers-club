package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.CheckService;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(CheckController.class)
class CheckControllerTest {

    @Autowired
    WebTestClient client;

    @MockBean
    private CheckService service;

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


}