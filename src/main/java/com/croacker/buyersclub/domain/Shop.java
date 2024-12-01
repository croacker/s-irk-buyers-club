package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Магазин.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table
public class Shop {
    @Id
    private Long id;

    /**
     * Наименование.
     */
    private String name;

    /**
     * Адрес.
     */
    private String address;

    /**
     * Организация.
     */
    private Long organizationId;

    /**
     * Создан.
     */
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
