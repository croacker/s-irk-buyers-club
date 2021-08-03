package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.TelegramUserService;
import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.mapper.telegramuser.FromToAddTelegramUser;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import com.croacker.buyersclub.service.telegram.request.TelegramMessage;
import com.croacker.buyersclub.telegram.chat.ChatPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MessageDispatcherImpl implements MessageDispatcher {

    private final TelegramFileService telegramFileService;

    private final ChatPool chatPool;

    private final LocaleService localeService;

    private final TelegramUserService telegramUserService;

    private final FromToAddTelegramUser telegramUserMapper;

    @Override
    public MessageProcessor getProcessor(TelegramMessage telegramMessage) {
        saveUser(telegramMessage);
        return switch (telegramMessage.getTelegramRequestType()) {
            case FILE -> createFileProcessor(telegramMessage);
            case COMMAND -> createCommandProcessor(telegramMessage);
            case CALLBACK -> createCallbackProcessor(telegramMessage);
            default -> createQueryProcessor(telegramMessage);
        };
    }

    private MessageProcessor createFileProcessor(TelegramMessage telegramMessage) {
        return new FileProcessor(telegramMessage, telegramFileService, localeService);
    }

    private MessageProcessor createCallbackProcessor(TelegramMessage telegramMessage) {
        return new CallbackProcessor(telegramMessage, localeService);
    }

    private MessageProcessor createCommandProcessor(TelegramMessage telegramMessage) {
        return new CommandProcessor(telegramMessage, localeService);
    }

    private MessageProcessor createQueryProcessor(TelegramMessage telegramMessage) {
        return new QueryProcessor(telegramMessage, chatPool, localeService);
    }

    private void saveUser(TelegramMessage telegramMessage) {
        var dto = telegramUserMapper.map(telegramMessage.getFrom());
        telegramUserService.findOne(dto.getId()).orElse(telegramUserService.save(dto));
    }

}
