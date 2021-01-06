package com.croacker.buyersclub.service.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Добавить товар.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Товар")
public class AddProductDto {
    /**
     * Наименование.
     */
    @Schema(description = "Наименование", example = "ООО 'Наименование'")
    private String name;

    /**
     * Идентификатор группы товаров.
     */
    @Schema(description = "Идентификатор группы товаров", example = "1")
    private Long productGroupId;
}
