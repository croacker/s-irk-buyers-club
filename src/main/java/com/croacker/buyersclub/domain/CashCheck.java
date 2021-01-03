package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Чек, шапка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class CashCheck {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Кассир.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cashier_id")
    private Cashier cashier;

    /**
     * Номер чека(???).
     */
    private String requestNumber;

    /**
     * Номер смены(???).
     */
    private String shiftNumber;

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
     * Товары.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "check_id")
    private List<CashCheckLine> checkLines;

    /**
     * telegram-пользователь добавивиший чек.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telegram_user_id")
    private TelegramUser telegramUser;

    /**
     * Создан.
     */
    private LocalDateTime createdAt;

    /**
     * Обновлен.
     */
    private LocalDateTime updatedAt;

    /**
     * Пометка на удаление.
     */
    private Boolean deleted;

}
