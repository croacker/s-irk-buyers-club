package com.croacker.buyersclub.service.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Магазин.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Магазин")
public class ShopDto {
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
     * Идентификатор организации.
     */
    @Schema(description = "Идентификатор организации", example = "1")
    private Long organizationId;

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
