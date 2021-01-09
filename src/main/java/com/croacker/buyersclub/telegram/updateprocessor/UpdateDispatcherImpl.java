package com.croacker.buyersclub.telegram.updateprocessor;

import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateDispatcherImpl implements UpdateDispatcher {

    private final TelegramFileService telegramFileService;

    private final LocaleService localeService;

    @Override
    public UpdateProcessor getProcessor(Update update) {
        var type = getMessageType(update);
        return switch (type) {
            case FILE -> createFileProcessor(update);
            case COMMAND -> createCommandProcessor(update);
            case CALLBACK -> createCallbackProcessor(update);
            default -> createQueryProcessor(update);
        };
    }

    private UpdateProcessor createFileProcessor(Update update) {
        return new FileProcessor(update.getMessage(), telegramFileService, localeService);
    }

    private UpdateProcessor createCallbackProcessor(Update update) {
        return new CallbackProcessor(update.getCallbackQuery());
    }

    private UpdateProcessor createCommandProcessor(Update update) {
        return new CommandProcessor(update.getMessage());
    }

    private UpdateProcessor createQueryProcessor(Update update) {
        return new QueryProcessor(update.getMessage());
    }

    private MessageType getMessageType(Update update){
        var result = MessageType.QUERY;
        if(isStart(update)){
            result = MessageType.COMMAND;
        }else if(isFile(update)) {
            result = MessageType.FILE;
        } else if (isCallback(update)){
            result = MessageType.CALLBACK;
        }
        return result;
    }

    /**
     * Получена команда start.
     * @param update
     * @return
     */
    private boolean isStart(Update update){
        return update.getMessage() != null
                && update.getMessage().hasText()
                && update.getMessage().getText().equals("/start");
    }

    /**
     * Получен файл.
     * @param update
     * @return
     */
    private boolean isFile(Update update) {
        return telegramFileService.getFileId(update.getMessage()).isPresent();
    }

    /**
     * Получен запрос, например товара.
     * @param update
     * @return
     */
    private boolean isCallback(Update update) {
        return update.getCallbackQuery() != null && update.getCallbackQuery().getMessage() != null;
    }

}
