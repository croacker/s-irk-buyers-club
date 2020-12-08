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
public class AddCashCheckLineDto {
    /**
     * Идентификатор товара.
     */
    @Schema(description = "Идентификатор товара", example = "1")
    private Long productId;

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

    /**
     * Пометка на удаление.
     */
    @Schema(description = "Пометка на удаление", example = "false")
    private Boolean deleted;
}
