package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.OfdCheckServiceImpl;
import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.telegram.file.FileInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO привести процессы в порядок.
@Service
@AllArgsConstructor
@Slf4j
public class IrkBuyersClubBot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;

    private final TelegramConfiguration configuration;

    private final WebClient client;

    private final OfdCheckServiceImpl ofdCheckService;

    private final ProductPriceService productPriceService;

    private final TelegramProductPriceDtoToString toStringMapper;

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
            var responseText = getResponseText(update.getMessage());
            execute(getHelpMessage(responseText, update.getMessage()));
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
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
            log.error(e.getMessage(), e);
        }
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

    private static SendMessage getHelpMessage(String responseText, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.enableMarkdown(true);
        sendMessage.setText(responseText);
        return sendMessage;
    }

    private String processFile(String fileId) {
        Mono<String> filePath = getFilePath(fileId);

        filePath.map(path -> {
            getFile(path).subscribe();
            return path;
        }).subscribe();
        return StringUtils.EMPTY;
    }

    /**
     * Получить ОФД чеки из файла.
     *
     * @param filePath
     * @return
     */
    private Flux<List<OfdCheck>> getFile(String filePath) {
        var url = "https://api.telegram.org/file/bot" + getBotToken() + "/" + filePath;
        log.info("File url:{}", url);
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(String.class)
                .map(str -> {// TODO если json отформатирован, он будет поступать построчно и не может быть десериализован
                    List<OfdCheck> ofdChecks = Collections.EMPTY_LIST;
                    var objectMapper = new ObjectMapper();
                    try {
                        ofdChecks = objectMapper.readValue(str, new TypeReference<>() {
                        });
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage(), e);
                    }
                    log.info("OfdChecks:{}", ofdChecks);
                    ofdChecks.forEach(ofdCheck -> ofdCheckService.process(ofdCheck));
                    return ofdChecks;
                });
    }

    private Optional<String> getFileId(Message message) {
        Optional<String> result = Optional.empty();
        if (message.getDocument() != null) {
            result = Optional.of(message.getDocument().getFileId());
        }
        return result;
    }

    private Mono<String> getFilePath(String fileId) {
        var url = "https://api.telegram.org/bot" + getBotToken() + "/getFile?file_id=" + fileId;
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(FileInfo.class)
                .map(fileInfo -> fileInfo.getResult().getFilePath());
    }

    private boolean isErrorResponse(HttpStatus status) {
        return status.is4xxClientError() || status.is5xxServerError();
    }

    /**
     * Ответ на запрос цены.
     * @param message
     * @return текст с ценами
     */
    private String getResponseText(Message message) {
        String result = "Спасибо";
        var expression = message.getText();
        if (expression != null) {
            result = productPriceService.getProductsPrices(expression.trim())
                    .stream().limit(10).map(toStringMapper).collect(Collectors.joining("\n "));
        }
        if(result.isEmpty()){
            result = "Нет данных";
        }
        return result;
    }

}
