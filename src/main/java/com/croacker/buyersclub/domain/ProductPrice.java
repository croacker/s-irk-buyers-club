package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Цена.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table
public class ProductPrice {

    @Id
    private Long id;

    /**
     * Магазин.
     */
    private Long shopId;

    /**
     * Товар.
     */
    private Long productId;

    /**
     * Цена.
     */
    private Integer price;

    /**
     * Дата-время.
     */
    private LocalDateTime priceDate;

    /**
     * Пометка на удаление.
     */
    private Boolean deleted;

}
