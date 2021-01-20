package com.croacker.buyersclub.telegram;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.locale.LocaleService;
import com.croacker.buyersclub.service.telegram.TelegramFileService;
import com.croacker.buyersclub.telegram.chat.Chat;
import com.croacker.buyersclub.telegram.chat.ChatFactory;
import com.croacker.buyersclub.telegram.keyboard.MenuKeyboardBuilder;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

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
        Mono.just(update).map(this::process).subscribe(this::sendResponse);
        getInprocessMessage(update).ifPresent(this::sendResponse);
//        try {
//            processFile(update.getMessage());
//            if (isStart(update)) {
////                execute(startMenu(update.getMessage()));
//            } else if (isSelectChatType(update)) {
//                var chat = createChat(update);
//                execute(getMessage(chat.getDescription(), chat.getChatId()));
//            } else {
//                var message = update.getMessage();
//                var response = getResponseText(update.getMessage());
//                execute(getMessage(response, message));
//            }
//        } catch (TelegramApiException e) {
//            log.error(e.getMessage(), e);
//        }
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

    private SendMessage process(Update update) {
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
        return getMessage(chatId, text);
    }

    private SendMessage queryInprocess(String chatId, String languageCode){
        var text = getString("response.query.inprocess", languageCode);
        return getMessage(chatId, text);
    }

    private SendMessage getMessage(String chatId, String responseText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(responseText);
        return sendMessage;
    }

//    private SendMessage getMessage(ReplyKeyboard keyboard, Message message) {
//        var chatId = String.valueOf(message.getChatId());
//        var languageCode = getLanguageCode(message);
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.enableMarkdown(true);
//        sendMessage.setText(getString("response.search.caption", languageCode));
//        sendMessage.setReplyMarkup(keyboard);
//        return sendMessage;
//    }

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

//    /**
//     * Получить и обработать файл, если он есть.
//     * @param message
//     */
//    private void processFile(Message message) {
//        telegramFileService.processFile(message);
//    }

//    /**
//     * Получена команда start.
//     * @param update
//     * @return
//     */
//    private boolean isStart(Update update){
//        return update.getMessage() != null
//                && update.getMessage().hasText()
//                && update.getMessage().getText().equals("/start");
//    }

//    private Chat createDefaultChat(Long chatId) {
//        var chat = chatFactory.createChat(chatId, "product");
//        chatPool.put(chatId, chat);
//        return chat;
//    }

//    private Chat getChat(Long chatId){
//        var chat = chatPool.get(chatId);
//        if (chat == null){
//            chat = createDefaultChat(chatId);
//        }
//        return chat;
//    }

//    /**
//     * Ответ на запрос цены.
//     * @param message
//     * @return текст с ценами
//     */
//    private ReplyKeyboard getResponseText(Message message) {
//        var languageCode = getLanguageCode(message);
//        ReplyKeyboard result = null;
//        var expression = message.getText();
//        if (expression != null) {
//            var chatId = message.getChatId();
//            var chat = getChat(chatId);
//            result = chat.findByName2(expression);
//        }
//        if(result == null){
//            var builder = new MenuKeyboardBuilder();
//            var text = getString("message.nodata", languageCode);
//            builder.newButton().setText(text).setData(text);
//            result = builder.build();
//        }
//        return result;
//    }

    private String getChatId(Update update) {
        return String.valueOf(update.getMessage().getChatId());
    }

    private String getLanguageCode(Update update){
        return update.getMessage().getFrom().getLanguageCode();
    }

    private String getString(String key, String languageCode){
        return localeService.getString(key, languageCode);
    }
}
