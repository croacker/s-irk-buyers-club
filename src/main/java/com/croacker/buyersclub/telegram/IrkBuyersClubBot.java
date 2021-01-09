package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatFactory;
import com.croacker.buyersclub.telegram.keyboard.MenuKeyboardBuilder;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.Map;

// TODO привести процессы в порядок.
@Service
@AllArgsConstructor
@Slf4j
public class IrkBuyersClubBot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;

    private final TelegramBotsApi telegramBotsApi;

    private final LocaleService localeService;

    private final TelegramConfiguration configuration;

    private final TelegramFileService telegramFileService;

    private final ChatFactory chatFactory;

    private final UpdateDispatcher updateDispatcher;

    private Map<Long, Chat> chatPool;

    @Override
    public String getBotUsername() {
        return configuration.getUsername();
    }

    @Override
    public String getBotToken() {
        return configuration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {// TODO разделить на операции
        var response = getProcessor(update).process();
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }

        try {
            processFile(update.getMessage());
            if (isStart(update)) {
//                execute(startMenu(update.getMessage()));
            } else if (isSelectChatType(update)) {
                var chat = createChat(update);
                execute(getMessage(chat.getDescription(), chat.getChatId()));
            } else {
                var message = update.getMessage();
                var response = getResponseText(update.getMessage());
                execute(getMessage(response, message));
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

    private SendMessage getMessage(String responseText, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(responseText);
        return sendMessage;
    }

    private SendMessage getMessage(ReplyKeyboard keyboard, Message message) {
        var chatId = String.valueOf(message.getChatId());
        var languageCode = getLanguageCode(message);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(getString("response.search.caption", languageCode));
        sendMessage.setReplyMarkup(keyboard);
        return sendMessage;
    }

//    public SendMessage startMenu(Message message) {
//        var languageCode = getLanguageCode(message);
//        var builder = new MenuKeyboardBuilder();
//        builder.newButton().setText(getString("menu.start.products", languageCode)).setData("product");
//        builder.newButton().setText(getString("menu.start.shops", languageCode)).setData("shop");
//        builder.newButton().setText(getString("menu.start.Organizations", languageCode)).setData("organization");
//        var sendMessage = new SendMessage();
//        sendMessage.setChatId(String.valueOf(message.getChatId()));
//        sendMessage.setText(getString("message.choosetype", languageCode));
//        sendMessage.setReplyMarkup(builder.build());
//        return sendMessage;
//    }

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

    private Chat createChat(Update update) {
        var chatId = update.getCallbackQuery().getMessage().getChatId();
        var type = update.getCallbackQuery().getData();
        var chat = chatFactory.createChat(chatId, type);
        chatPool.put(chatId, chat);
        return chat;
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
    private ReplyKeyboard getResponseText(Message message) {
        var languageCode = getLanguageCode(message);
        ReplyKeyboard result = null;//getString("message.thankyou", languageCode);
        var expression = message.getText();
        if (expression != null) {
            var chatId = message.getChatId();
            var chat = getChat(chatId);
            result = chat.findByName2(expression);
        }
        if(result == null){
            var builder = new MenuKeyboardBuilder();
            var text = getString("message.nodata", languageCode);
            builder.newButton().setText(text).setData(text);
            result = builder.build();
        }
        return result;
    }

    private String getLanguageCode(Message message){
        return message.getFrom().getLanguageCode();
    }

    private String getString(String key, String languageCode){
        return localeService.getString(key, languageCode);
    }
}
