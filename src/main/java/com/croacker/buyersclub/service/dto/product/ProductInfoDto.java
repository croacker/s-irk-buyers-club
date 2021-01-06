package com.croacker.buyersclub.service.dto.product;

import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
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
public class ProductInfoDto {
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
     * Наименование группы товаров.
     */
    @Schema(description = "Наименование группы товаров", example = "1")
    private String productGroupName;

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
     * Цена.
     */
    @Schema(description = "Цена", example = "false")
    private ProductPriceDto price;

    /**
     * Пометка на удаление.
     */
    @Schema(description = "Пометка на удаление", example = "false")
    private Boolean deleted;

}
