package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
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

@Tag(name = "Cashier", description = "Кассиры")
public interface CashierOperations {

    @Operation(operationId = "listCashiers", summary = "Список кассиров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кассир",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Кассиры не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<CashierDto> getAllCashiers(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "20") int size,
                                         @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                         @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction);

    @Operation(operationId = "getCashier", summary = "Получить кассира по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кассир",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Кассир не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/{id}")
    Mono<CashierDto> getCashier(@PathVariable Long id);

    @Operation(operationId = "createCashier", summary = "Добавить кассира",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кассир",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PostMapping
    Mono<CashierDto> createCashier(@RequestBody AddCashierDto dto);

    @Operation(operationId = "updateCashier", summary = "Обновить кассира",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кассир",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PutMapping
    Mono<CashierDto> updateCashier(@RequestBody CashierDto dto);

    @Operation(operationId = "deleteCashier", summary = "Удалить кассира по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кассир",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Кассир не найден", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @DeleteMapping(path = "/{id}")
    Mono<CashierDto> deleteCashier(@PathVariable Long id);

}
