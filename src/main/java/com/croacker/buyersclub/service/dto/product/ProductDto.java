package com.croacker.buyersclub.service.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Товар.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Товар")
public class ProductDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

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

    /**
     * Тип товара.
     */
    @Schema(description = "Тип товара", example = "1")
    private Integer productType;

    /**
     * Создан.
     */
    @Schema(description = "Создан")
    private LocalDateTime createdAt;

    /**
     * Обновлен.
     */
    @Schema(description = "Обновлен")
    private LocalDateTime updatedAt;

    /**
     * Пометка на удаление.
     */
    @Schema(description = "Пометка на удаление", example = "false")
    private Boolean deleted;
}
