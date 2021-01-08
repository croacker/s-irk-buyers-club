package com.croacker.buyersclub.telegram.chat;

import com.croacker.buyersclub.service.OrganizationService;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.mapper.telegram.TelegramOrganizationDtoToString;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;
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
        return getOrganizations(expression)
                .stream().map(toStringMapper).collect(Collectors.joining(LINE_DELIMITER));
    }

    @Override
    public ReplyKeyboard findByName2(String expression) {
        return null;
    }

    @Override
    public String getDescription() {
        return "Поиск организаций";
    }

    private List<OrganizationDto> getOrganizations(String expression){
        var pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        return organizationService.getOrganizations(expression.trim(), pageable);
    }
}
