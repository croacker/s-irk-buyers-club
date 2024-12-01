package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Товар.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table
public class Product {

    @Id
    private Long id;

    /**
     * Наименование.
     */
    private String name;

    /**
     * Группа.
     */
    private Long productGroupId;

    /**
     * Тип товара.
     */
    private ProductType productType;

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
