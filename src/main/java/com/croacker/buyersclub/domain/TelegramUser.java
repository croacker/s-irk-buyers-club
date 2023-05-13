package com.croacker.buyersclub.domain;

import io.swagger.v3.oas.annotations.media.Schema;
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
