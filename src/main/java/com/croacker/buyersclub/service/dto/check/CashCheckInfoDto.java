package com.croacker.buyersclub.service.dto.check;

import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Чек, шапка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Чек, шапка")
public class CashCheckInfoDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Идентификатор кассира.
     */
    @Schema(description = "Идентификатор кассира", example = "1")
    private Long cashierId;

    /**
     * Имя кассира.
     */
    @Schema(description = "Рег.номер кассового аппарата", example = "name")
    private String cashierName;

    /**
     * Позиции в чеке.
     */
    @Schema(description = "Позиции в чеке")
    private List<CashCheckLineDto> checkLines;

    /**
     * Рег.номер кассового аппарата(имя атрибута оригинальное).
     */
    @Schema(description = "Рег.номер кассового аппарата", example = "987654321")
    private String kktRegId;

    /**
     * Номер платы.
     */
    @Schema(description = "Номер платы", example = "987654321")
    private String fiscalDriveNumber;

    /**
     * Номер документа.
     */
    @Schema(description = "Номер документа", example = "987654321")
    private String fiscalDocumentNumber;

    /**
     * Сумма.
     */
    @Schema(description = "Сумма", example = "10000")
    private Integer totalSum;

    /**
     * Сумма наличные.
     */
    @Schema(description = "Сумма наличные", example = "5000")
    private Integer cashSum;

    /**
     * Сумма безналичные.
     */
    @Schema(description = "Сумма безналичные", example = "5000")
    private Integer ecashSum;

    /**
     * Дата-время.
     */
    @Schema(description = "Сумма безналичные")
    private LocalDateTime checkDate;

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
