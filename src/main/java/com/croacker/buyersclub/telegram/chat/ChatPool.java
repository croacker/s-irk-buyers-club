package com.croacker.buyersclub.telegram.chat;

@Deprecated
public interface ChatPool {

    Chat getChat(String id);

    Chat createChat(String id, ChatType type);

}
