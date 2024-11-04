package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Чек, шапка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table
public class CashCheck {

    @Id
    private Long id;

    /**
     * Кассир.
     */
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
    @MappedCollection(idColumn = "check_id")
    private Set<CashCheckLine> checkLines;

    /**
     * telegram-пользователь добавивиший чек.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telegram_user_id")
    private TelegramUser telegramUser;

    /**
     * Создан.
     */
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Обновлен.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Пометка на удаление.
     */
    private Boolean deleted;

}
