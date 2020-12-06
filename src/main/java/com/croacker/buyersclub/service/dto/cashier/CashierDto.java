package com.croacker.buyersclub.service.dto.cashier;

import com.croacker.buyersclub.domain.Shop;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Кассир.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Кассир")
public class CashierDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

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
