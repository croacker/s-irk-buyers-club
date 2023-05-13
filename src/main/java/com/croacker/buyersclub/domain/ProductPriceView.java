package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
