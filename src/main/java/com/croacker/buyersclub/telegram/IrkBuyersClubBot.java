package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.telegram.updateprocessor.MessageType;
import com.croacker.buyersclub.telegram.updateprocessor.UpdateDispatcher;
import com.croacker.buyersclub.telegram.updateprocessor.UpdateProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class IrkBuyersClubBot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;

    private final TelegramBotsApi telegramBotsApi;

    private final LocaleService localeService;

    private final TelegramConfiguration configuration;

    private final UpdateDispatcher updateDispatcher;

    @Override
    public String getBotUsername() {
        return configuration.getUsername();
    }

    @Override
    public String getBotToken() {
        return configuration.getToken();
    }

    @PostConstruct
    public void init() {
        botConnect();
    }

    @Override
    public void onUpdateReceived(Update update) {// TODO разделить на операции
        getInprocessMessage(update).ifPresent(this::sendResponse);
        Mono.just(update).flatMap(this::process).subscribe(this::sendResponse);
    }

    private Optional<SendMessage> getInprocessMessage(Update update) {
        var chatId = getChatId(update);
        var languageCode = getLanguageCode(update);
        return switch (getMessageType(update)){
            case FILE -> Optional.of(fileInprocess(chatId, languageCode));
            case QUERY -> Optional.of(queryInprocess(chatId, languageCode));
            default -> Optional.empty();
        };
    }

    private void sendResponse(SendMessage response) {
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Mono<SendMessage> process(Update update) {
        return getProcessor(update).process();
    }

    private void botConnect() {
        try {
            telegramBotsApi.registerBot(this);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            log.info("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                log.error(e1.getMessage(), e1);
                return;
            }
            botConnect();
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    private UpdateProcessor getProcessor(Update update){
        return updateDispatcher.getProcessor(update);
    }

    private MessageType getMessageType(Update update){
        return updateDispatcher.getMessageType(update);
    }

    private SendMessage fileInprocess(String chatId, String languageCode){
        var text = getString("response.file.inprocess", languageCode);
        return getResponse(chatId, text);
    }

    private SendMessage queryInprocess(String chatId, String languageCode){
        var text = getString("response.query.inprocess", languageCode);
        return getResponse(chatId, text);
    }

    private SendMessage getResponse(String chatId, String responseText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(responseText);
        return sendMessage;
    }

    private String getChatId(Update update) {
        return String.valueOf(getMessage(update).getChatId());
    }

    /**
     *
     * @param update
     * @return
     */
    private Message getMessage(Update update){
        var message = update.getMessage();
        if (message == null){
            message = update.getCallbackQuery().getMessage();
        }
        return message;
    }

    private String getLanguageCode(Update update){
        return getMessage(update).getFrom().getLanguageCode();
    }

    private String getString(String key, String languageCode){
        return localeService.getString(key, languageCode);
    }
}
