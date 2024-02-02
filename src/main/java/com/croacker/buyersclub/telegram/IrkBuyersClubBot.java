package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramMessageServiceImpl;
import com.croacker.buyersclub.service.telegram.request.TelegramCallback;
import com.croacker.buyersclub.service.telegram.request.TelegramCommand;
import com.croacker.buyersclub.service.telegram.request.TelegramFile;
import com.croacker.buyersclub.service.telegram.request.TelegramMessage;
import com.croacker.buyersclub.service.telegram.request.TelegramQuery;
import com.croacker.buyersclub.service.telegram.request.TelegramRequestType;
import com.croacker.buyersclub.telegram.updateprocessor.MessageDispatcher;
import com.croacker.buyersclub.telegram.updateprocessor.MessageProcessor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IrkBuyersClubBot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;

    private final TelegramBotsApi telegramBotsApi;

    private final LocaleService localeService;

    private final TelegramConfiguration configuration;

    private final MessageDispatcher messageDispatcher;

    private final TelegramMessageServiceImpl telegramMessageService;

    ExecutorService executorService = Executors.newFixedThreadPool(1);

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
        executorService.submit(this::botConnect);
    }

    @Override
    public void onUpdateReceived(Update update) {
        var telegramMessage = toTelegramMessage(update);
        getInprocessMessage(telegramMessage).ifPresent(this::sendResponse);
        Mono.just(telegramMessage).flatMap(this::process).subscribe(this::sendResponse);
    }

    /**
     * Сообщение о том что запрос обрабатывается.
     * @param update сообщение от пользователя
     * @return ответ
     */
    private Optional<SendMessage> getInprocessMessage(TelegramMessage update) {
        return switch (update.getTelegramRequestType()){
            case FILE -> Optional.of(fileInprocess(update));
            case QUERY -> Optional.of(queryInprocess(update));
            default -> Optional.empty();
        };
    }

    /**
     * Отправить сообщение.
     * @param response сообщение
     */
    private void sendResponse(SendMessage response) {
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Mono<SendMessage> process(TelegramMessage telegramMessage) {
        return getProcessor(telegramMessage).process();
    }

    private void botConnect() {
        try {
            telegramBotsApi.registerBot(this);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            log.info("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: {}", e.getMessage());
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

    private MessageProcessor getProcessor(TelegramMessage telegramMessage){
        return messageDispatcher.getProcessor(telegramMessage);
    }

    private TelegramRequestType getMessageType(Update update){
        return telegramMessageService.getMessageType(update);
    }

    // TODO to service
    private SendMessage fileInprocess(TelegramMessage telegramMessage){
        var text = getString("response.file.inprocess", telegramMessage.getLanguageCode());
        return getResponse(telegramMessage.getChatId(), text);
    }

    // TODO to service
    private SendMessage queryInprocess(TelegramMessage telegramMessage){
        var text = getString("response.query.inprocess", telegramMessage.getLanguageCode());
        return getResponse(telegramMessage.getChatId(), text);
    }

    // TODO to service
    private SendMessage getResponse(String chatId, String responseText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(responseText);
        return sendMessage;
    }

    private String getString(String key, String languageCode){
        return localeService.getString(key, languageCode);
    }

    private TelegramMessage toTelegramMessage(Update update) {
        var type = getMessageType(update);
        return switch (type) {
            case FILE -> new TelegramFile(type, update);
            case COMMAND -> new TelegramCommand(type, update);
            case CALLBACK -> new TelegramCallback(type, update);
            default -> new TelegramQuery(type, update);
        };
    }

}
