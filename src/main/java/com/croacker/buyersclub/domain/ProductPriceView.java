package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Представление цены, sql-view.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table
public class ProductPriceView {

    /**
     * Идентификатор цены.
     */
    @Id
    private Long id;

    /**
     * Идентификатор товара.
     */
    private Long productId;

    /**
     * Наименование товара.
     */
    private String productName;

    /**
     * Идентификатор магазина.
     */
    private Long shopId;

    /**
     * Наименование магазина.
     */
    private String shopName;

    /**
     * Цена.
     */
    private Integer price;

    /**
     * Дата-время.
     */
    private LocalDateTime priceDate;

}
