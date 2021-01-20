package com.croacker.buyersclub.telegram.chat;

public interface ChatPool {

    Chat getChat(Long id);

    Chat createChat(Long id, ChatType type);

}
