package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.io.Flushable;
import java.time.Duration;
import java.util.Optional;

import static java.time.Duration.ofMillis;

@Service
@AllArgsConstructor
public class IrkBuyersClubBot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;

    private final TelegramConfiguration configuration;

    private final WebClient client;

    @Override
    public String getBotUsername() {
        return configuration.getUsername();
    }

    @Override
    public String getBotToken() {
        return configuration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            getFileId(update.getMessage()).ifPresent(this::processFile);
            execute(getHelpMessage(update.getMessage()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        botConnect();
    }

    private void botConnect() {
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        try {
            telegramBotsApi.registerBot(this);
            System.out.println("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            System.out.println("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static SendMessage getHelpMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.enableMarkdown(true);
        sendMessage.setText("testmessage");
        return sendMessage;
    }

    private Optional<String> getFileId(Message message) {
        Optional<String> result = Optional.empty();
        if (message.getDocument() != null) {
            result = Optional.of(message.getDocument().getFileId());
        }
        return result;
    }

    private String processFile(String fileId) {
        var url = "https://api.telegram.org/bot" + getBotToken() + "/getFile?file_id=" + fileId;
        Flux<DataBuffer> resu = client.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        resu.map(dataBuffer -> dataBuffer.asByteBuffer())
                .map(byteBuffer -> {
                    var s = new String(byteBuffer.array());
                    System.out.println(s);
                    return s;
                }).subscribe(c -> DataBufferUtils.releaseConsumer());
//                .onStatus(this::isErrorResponse, WebUtils::wrapResponseError)
//                .bodyToMono(GetFileResponse.class)
//                .retryWhen(backoff(cfg.getMaxAttempts(), ofMillis(cfg.getMinBackoffMs()))
//                        .filter(WebUtils::isRetryException))
//                .timeout(Duration.ofSeconds(cfg.getCommonTimeoutSec()))
//                .onErrorMap(WebUtils::wrapException);
        return StringUtils.EMPTY;
    }

    private boolean isErrorResponse(HttpStatus status){
        return status.is4xxClientError() || status.is5xxServerError();
    }
}
