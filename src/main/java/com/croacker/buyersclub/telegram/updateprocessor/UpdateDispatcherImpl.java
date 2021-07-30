package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import com.croacker.buyersclub.service.telegram.TelegramMessageService;
import com.croacker.buyersclub.service.telegram.TelegramTelegramUserService;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateDispatcherImpl implements UpdateDispatcher {

    private final TelegramFileService telegramFileService;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    private final TelegramMessageService telegramMessageService;

    private final TelegramTelegramUserService telegramTelegramUserService;

    @Override
    public UpdateProcessor getProcessor(Update update) {
        saveUser(update);
        var type = getMessageType(update);
        return switch (type) {
            case FILE -> createFileProcessor(update);
            case COMMAND -> createCommandProcessor(update);
            case CALLBACK -> createCallbackProcessor(update);
            default -> createQueryProcessor(update);
        };
    }

    private MessageType getMessageType(Update update) {
        return telegramMessageService.getMessageType(update);
    }

    private UpdateProcessor createFileProcessor(Update update) {
        return new FileProcessor(update.getMessage(), telegramFileService, chatPool, localeService, telegramTelegramUserService);
    }

    private UpdateProcessor createCallbackProcessor(Update update) {
        return new CallbackProcessor(update.getCallbackQuery(), chatPool, localeService);
    }

    private UpdateProcessor createCommandProcessor(Update update) {
        return new CommandProcessor(update.getMessage(), chatPool, localeService);
    }

    private UpdateProcessor createQueryProcessor(Update update) {
        return new QueryProcessor(update.getMessage(), chatPool, localeService);
    }

    private void saveUser(Update update) {
        var type = getMessageType(update);
        var from = switch (type) {
            case CALLBACK -> update.getCallbackQuery().getFrom();
            default -> telegramMessageService.getMessage(update).map(Message::getFrom).get();
        };
        telegramTelegramUserService.saveUser(from);
    }
}
