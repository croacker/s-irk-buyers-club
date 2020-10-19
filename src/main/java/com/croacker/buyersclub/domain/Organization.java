package com.croacker.buyersclub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * Организации.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Organization {
    @Id
    private Long id;

    /**
     * Наименование.
     */
    private String name;

    /**
     * ИНН.
     */
    private String inn;

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
    private Boolean deleted = false;
}
