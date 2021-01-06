package com.croacker.buyersclub.service.dto.checkline;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Чек, строка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Чек, шапка")
public class CashCheckLineInfoDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Идентификатор товара.
     */
    @Schema(description = "Идентификатор товара", example = "1")
    private Long productId;

    /**
     * Наименование товара.
     */
    @Schema(description = "Идентификатор товара", example = "product")
    private String productName;

    /**
     * Цена.
     */
    @Schema(description = "Цена", example = "10000")
    private Integer price;

    /**
     * Количество.
     */
    @Schema(description = "Количество", example = "2")
    private Integer quantity;

    /**
     * Сумма.
     */
    @Schema(description = "Сумма", example = "20000")
    private Integer totalSum;

}
