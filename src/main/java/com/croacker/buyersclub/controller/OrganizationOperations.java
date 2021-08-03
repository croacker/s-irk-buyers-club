package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "Organization", description = "Организации")
public interface OrganizationOperations {

    @Operation(operationId = "listOrganizations", summary = "Список организаций")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Организации",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Организации не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<OrganizationDto> getAllOrganizations(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "20") int size,
                                              @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                              @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction);

    @Operation(operationId = "getOrganizationsCount", summary = "Получить количество организаций в БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/count")
    Mono<Long> getOrganizationsCount();

    @Operation(operationId = "getOrganization", summary = "Получить организацию по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Организация",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Организация не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/{id}")
    Mono<OrganizationDto> getOrganization(@PathVariable Long id);

    @Operation(operationId = "createOrganization", summary = "Добавить организацию",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "организация",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PostMapping
    Mono<OrganizationDto> createOrganization(@RequestBody AddOrganizationDto dto);

    @Operation(operationId = "updateOrganization", summary = "Обновить организацию",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "организация",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PutMapping
    Mono<OrganizationDto> updateOrganization(@RequestBody OrganizationDto dto);

    @Operation(operationId = "deleteOrganization", summary = "Удалить организацию по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "организация",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Организация не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @DeleteMapping(path = "/{id}")
    Mono<OrganizationDto> deleteOrganization(@PathVariable Long id);
}
