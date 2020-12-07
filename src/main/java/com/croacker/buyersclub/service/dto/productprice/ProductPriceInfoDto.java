package com.croacker.buyersclub.service.dto.productprice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Цена на товар, с наименованиями магазина и товара.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Цена на товар")
public class ProductPriceInfoDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Идентификатор магазина.
     */
    @Schema(description = "Идентификатор магазина", example = "1")
    private Long shopId;

    /**
     * Наименование магазина.
     */
    @Schema(description = "Идентификатор магазина", example = "1")
    private String shopName;

    /**
     * Идентификатор товара.
     */
    @Schema(description = "Идентификатор товара", example = "1")
    private Long productId;

    /**
     * Наименование товара.
     */
    @Schema(description = "Идентификатор товара", example = "1")
    private String productName;

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
    private Boolean deleted;
}
