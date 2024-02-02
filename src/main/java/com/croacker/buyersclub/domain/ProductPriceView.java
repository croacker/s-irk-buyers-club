package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Представление цены, sql-view.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
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
