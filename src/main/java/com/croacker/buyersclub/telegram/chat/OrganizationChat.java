package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramOrganizationDtoToString;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

/**
 * Чат организации
 */
@AllArgsConstructor
public class OrganizationChat implements Chat{

    private final Long chatId;

    private final OrganizationService organizationService;

    private final TelegramOrganizationDtoToString toStringMapper;

    @Override
    public String getChatId() {
        return String.valueOf(chatId);
    }

    @Override
    public String findByName(String expression) {
        return organizationService.getOrganizations(expression)
                .stream().limit(10).map(toStringMapper).collect(Collectors.joining(LINE_DELIMITER));
    }

    @Override
    public String getDescription() {
        return "Поиск организаций";
    }

}
