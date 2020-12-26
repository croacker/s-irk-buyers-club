package com.croacker.buyersclub.telegram.chat;

public interface Chat {

    String getChatId();

    String findByName(String expression);

    String getDescription();
}
