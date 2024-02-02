package com.croacker.buyersclub.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Telegram-пользователь.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class TelegramUser {
    @Id
    private Long id;

    /**
     * Это бот.
     */
    private Boolean isBot;

    /**
     * Наименование.
     */
    private String userName;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Может присоединится к группе. Только для бота.
     */
    private Boolean canJoinGroups;

    /**
     * Может присоединится к группе.
     */
    private Boolean canReadAllGroupMessages;

    /**
     * .
     */
    private Boolean supportInlineQueries;
}
