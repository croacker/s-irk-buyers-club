package com.croacker.buyersclub.service.dto.check;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Добавить чек, шапка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Чек, шапка")
public class AddCheckDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

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
     * Пометка на удаление.
     */
    @Schema(description = "Пометка на удаление", example = "false")
    private Boolean deleted;
}
