package com.croacker.buyersclub.service.dto.productprice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Цена на товар.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Цена на товар")
public class ProductPriceDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Цена.
     */
    @Schema(description = "Цена", example = "1")
    private Integer price;

    /**
     * Дата-время.
     */
    @Schema(description = "Дата-время")
    private LocalDateTime priceDate;

    /**
     * Пометка на удаление.
     */
    @Schema(description = "Пометка на удаление", example = "false")
    private Boolean deleted = false;
}
