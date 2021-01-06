package com.croacker.buyersclub.service.dto.telegramuser;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * telegram-пользователь.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "telegram-пользователь")
public class AddTelegramUserDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Наименование.
     */
    @Schema(description = "Наименование")
    private String userName;

    /**
     * Имя.
     */
    @Schema(description = "Имя")
    private String firstName;

    /**
     * Фамилия.
     */
    @Schema(description = "Фамилия")
    private String lastName;
}
