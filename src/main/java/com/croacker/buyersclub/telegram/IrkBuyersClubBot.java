package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.ProductPriceService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import com.croacker.buyersclub.service.mapper.telegram.TelegramProductPriceDtoToString;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final ChatFactory chatFactory;

    private Map<Long, Chat> chatPool = new HashMap<>();

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
            if (isStart(update)) {
                execute(startMenu(update.getMessage()));
            } if (isSelectChatType(update)) {
                createChat(update);
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

    public static SendMessage startMenu(Message message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonProducts = new InlineKeyboardButton();
        buttonProducts.setText("Товары");
        buttonProducts.setCallbackData("product");
        InlineKeyboardButton buttonShops = new InlineKeyboardButton();
        buttonShops.setText("Магазины");
        buttonShops.setCallbackData("shop");
        InlineKeyboardButton buttonOrganizations = new InlineKeyboardButton();
        buttonOrganizations.setText("Организации");
        buttonOrganizations.setCallbackData("organization");
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
     * Выбран тип объекта.
     * @param update
     * @return
     */
    private boolean isSelectChatType(Update update) {
        return update.getMessage() == null && update.getCallbackQuery() != null;
    }

    private void createChat(Update update) {
        var chatId = update.getCallbackQuery().getMessage().getChatId();
        var type = update.getCallbackQuery().getData();
        var chat = chatFactory.createChat(chatId, type);
        chatPool.put(chatId, chat);
    }

    private Chat createDefaultChat(Long chatId) {
        var chat = chatFactory.createChat(chatId, "product");
        chatPool.put(chatId, chat);
        return chat;
    }

    private Chat getChat(Long chatId){
        var chat = chatPool.get(chatId);
        if (chat == null){
            chat = createDefaultChat(chatId);
        }
        return chat;
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
            var chatId = message.getChatId();
            var chat = getChat(chatId);
            result = chat.findByName(expression);
        }
        if(result.isEmpty()){
            result = "Нет данных";
        }
        return result;
    }

}
