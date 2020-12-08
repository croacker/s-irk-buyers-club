package com.croacker.buyersclub.service.dto.check;

import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Добавить чек, шапка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Чек, шапка")
public class AddCashCheckDto {
    /**
     * Идентификатор кассира.
     */
    @Schema(description = "Идентификатор кассира", example = "1")
    private Long cashierId;

    /**
     * Номер чека(???).
     */
    @Schema(description = "Номер чека(???)", example = "987654321")
    private String requestNumber;

    /**
     * Номер смены(???).
     */
    @Schema(description = "Номер смены(???)", example = "987654321")
    private String shiftNumber;

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
     * Товары чека.
     */
    @Schema(description = "Товары чека")
    private List<AddCashCheckLineDto> checkLines;

}
