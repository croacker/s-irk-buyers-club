package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
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

@Tag(name = "Check", description = "Кассиры")
public interface CheckOperations {

    @Operation(operationId = "listChecks", summary = "Список чеков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чеки",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Чеки не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<CashCheckInfoDto> getAllChecks(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "20") int size,
                                    @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                    @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction);

    @Operation(operationId = "getCheck", summary = "Получить чек по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чек",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashCheckInfoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Чек не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/{id}")
    Mono<CashCheckInfoDto> getCheck(@PathVariable Long id);

    @Operation(operationId = "createCheck", summary = "Добавить чек",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чек",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashCheckDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PostMapping
    Mono<CashCheckDto> createCheck(@RequestBody AddCashCheckDto dto);

    @Operation(operationId = "updateCheck", summary = "Обновить чек",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чек",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashCheckDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PutMapping
    Mono<CashCheckDto> updateCheck(@RequestBody CashCheckDto dto);

    @Operation(operationId = "deleteCheck", summary = "Удалить чек по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чек",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashCheckDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Чек не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @DeleteMapping(path = "/{id}")
    Mono<CashCheckDto> deleteCheck(@PathVariable Long id);

}
