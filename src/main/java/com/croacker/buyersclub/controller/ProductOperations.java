package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "Product", description = "Товары")
public interface ProductOperations {

    @Operation(operationId = "listProducts", summary = "Список товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Товары не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<ProductDto> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "20") int size,
                                    @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                    @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction);

    @Operation(operationId = "getProduct", summary = "Получить товар по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Товар не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/{id}")
    Mono<ProductDto> getProduct(@PathVariable Long id);

    @Operation(operationId = "createProduct", summary = "Добавить товар",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PostMapping
    Mono<ProductDto> createProduct(@RequestBody AddProductDto dto);

    @Operation(operationId = "updateProduct", summary = "Обновить товар",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PutMapping
    Mono<ProductDto> updateProduct(@RequestBody ProductDto dto);

    @Operation(operationId = "deleteProduct", summary = "Удалить товар по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Товар не найден", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @DeleteMapping(path = "/{id}")
    Mono<ProductDto> deleteProduct(@PathVariable Long id);

}
