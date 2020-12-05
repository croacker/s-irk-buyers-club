package com.croacker.buyersclub.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class IrkBuyersClubBot extends TelegramLongPollingBot {

    final int RECONNECT_PAUSE =10000;

    @Value("${app.telegram.token}")
    private String botToken;

    @Value("${app.telegram.username}")
    private String botUsername;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            getFile(update.getMessage());
            execute(getHelpMessage(update.getMessage()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void botConnect() {
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
        sendMessage.setText("test_message");
        return sendMessage;
    }

    private void getFile(Message message){
        if (message.getDocument() != null){
            System.out.println(message.getDocument());
        }
    }
}
