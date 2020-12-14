package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.TelegramFileService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO привести процессы в порядок.
@Service
@AllArgsConstructor
@Slf4j
public class IrkBuyersClubBot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;

    private final TelegramConfiguration configuration;

    private final TelegramFileService telegramFileService;

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
            processFile(update.getMessage());
            if (update.getMessage() != null
                    && update.getMessage().hasText()
                    && update.getMessage().getText().equals("/start")) {
                execute(sendInlineKeyBoardMessage(update.getMessage()));
            } else {
                var responseText = getResponseText(update.getMessage());
                execute(getHelpMessage(responseText, update.getMessage()));
            }
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

    public static SendMessage sendInlineKeyBoardMessage(Message message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonProducts = new InlineKeyboardButton();
        buttonProducts.setText("Товары");
        buttonProducts.setCallbackData("products");
        InlineKeyboardButton buttonShops = new InlineKeyboardButton();
        buttonShops.setText("Магазины");
        buttonShops.setCallbackData("shops");
        InlineKeyboardButton buttonOrganizations = new InlineKeyboardButton();
        buttonOrganizations.setText("Организации");
        buttonOrganizations.setCallbackData("organizations");
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(buttonProducts);
        row.add(buttonShops);
        row.add(buttonOrganizations);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        inlineKeyboardMarkup.setKeyboard(rowList);
        var sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText("Выберите тип");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    /**
     * Получить и обработать файл, если он есть.
     * @param message
     */
    private void processFile(Message message) {
        telegramFileService.processFile(message);
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
