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
}
