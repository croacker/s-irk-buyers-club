package com.croacker.buyersclub.service.dto.cashier;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Добавить кассира.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Кассир")
public class AddCashierDto {
    /**
     * Наименование.
     */
    @Schema(description = "Наименование", example = "Кассир К.К.")
    private String name;

    /**
     * Идентификатор магазина.
     */
    @Schema(description = "Идентификатор магазина", example = "1")
    private Long shopId;

}
