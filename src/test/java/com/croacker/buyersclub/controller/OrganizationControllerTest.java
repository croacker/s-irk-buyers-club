package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
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

@WebFluxTest(OrganizationController.class)
class OrganizationControllerTest {

    @Autowired
    WebTestClient client;

    @MockBean
    private OrganizationService service;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @Test
    public void shouldReturnAllOrganizations() {
        // given
        var given1 = createOrganizationDto(1L);
        var given2 = createOrganizationDto(2L);
        when(service.findAll(any())).thenReturn(Arrays.asList(given1, given2));

        // when and then
        client.get()
                .uri("/api/v1/organization")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(organization -> organization.size(), equalTo(2));
    }

    @Test
    public void shouldReturnOrganization() {
        // given
        var expected = createOrganizationDto(1L);
        when(service.findOne(any())).thenReturn(expected);

        // when and then
        client.get()
                .uri("/api/v1/organization/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrganizationDto.class)
                .value(organization -> organization, equalTo(expected));
    }

    @Test
    public void shouldCreateOrganization() {
        // given
        var given = createAddOrganizationDto(1L);
        var expected = createOrganizationDto(1L);
        when(service.save(given)).thenReturn(expected);

        // when and then
        client.post()
                .uri("/api/v1/organization")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrganizationDto.class)
                .value(organization -> organization, equalTo(expected));
    }

    @Test
    public void shouldUpdateOrganization() {
        // given
        var given = createOrganizationDto(1L);
        var expected = createOrganizationDto(1L);
        when(service.update(given)).thenReturn(expected);

        // when and then
        client.put()
                .uri("/api/v1/organization")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrganizationDto.class)
                .value(organization -> organization, equalTo(expected));
    }

    @Test
    public void shouldDeleteOrganization() {
        // given
        var expected = createOrganizationDto(1L).setDeleted(true);
        when(service.delete(1L)).thenReturn(expected);

        // when and then
        client.delete()
                .uri("/api/v1/organization/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrganizationDto.class)
                .value(organization -> organization, equalTo(expected));
    }

    private OrganizationDto createOrganizationDto(long id) {
        return testEntitiesProducer.createOrganizationDto(id);
    }

    private AddOrganizationDto createAddOrganizationDto(long id) {
        return testEntitiesProducer.createAddOrganizationDto(id);
    }
}