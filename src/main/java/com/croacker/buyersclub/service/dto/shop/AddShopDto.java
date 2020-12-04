package com.croacker.buyersclub.service.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Добавить магазин.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Магазин")
public class AddShopDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Наименование.
     */
    @Schema(description = "Наименование", example = "Наименование")
    private String name;

    /**
     * Адрес.
     */
    @Schema(description = "Адрес", example = "г.Город,ул.Улица, д.Д.")
    private String address;

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
