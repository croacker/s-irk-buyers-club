package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Чек, шапка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Check {
    @Id
    private Long id;

    /**
     * Кассир.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id")
    private Cashier cashier;

    /**
     * Рег.номер кассового аппарата(имя атрибута оригинальное).
     */
    private String kktRegId;

    /**
     * Номер платы.
     */
    private String fiscalDriveNumber;

    /**
     * Номер документа.
     */
    private String fiscalDocumentNumber;

    /**
     * Сумма.
     */
    private Integer totalSum;

    /**
     * Сумма наличные.
     */
    private Integer cashSum;

    /**
     * Сумма безналичные.
     */
    private Integer ecashSum;

    /**
     * Дата-время.
     */
    private LocalDateTime checkDate;

    /**
     * Пометка на удаление.
     */
    private Boolean deleted = false;

}
