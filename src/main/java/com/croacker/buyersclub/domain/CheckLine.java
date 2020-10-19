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
 * Чек, строка.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CheckLine {
    @Id
    private Long id;

    /**
     * Чек.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_id")
    private Check check;

    /**
     * Товар.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Цена.
     */
    private Integer price;

    /**
     * Количество.
     */
    private Integer quantity;

    /**
     * Сумма.
     */
    private Integer totalSum;

    /**
     * Пометка на удаление.
     */
    private Boolean deleted = false;
    
}
