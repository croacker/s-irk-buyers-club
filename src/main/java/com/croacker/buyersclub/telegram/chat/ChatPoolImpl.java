package com.croacker.buyersclub.telegram.chat;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ChatPoolImpl implements ChatPool{

    private final ChatFactory chatFactory;

    private Map<Long, Chat> pool;

    @Override
    public Chat getChat(Long id) {
        return getFromPool(id).orElseGet(() -> createChat(id, ChatType.PRODUCT));
    }

    @Override
    public Chat createChat(Long id, ChatType type) {
        getFromPool(id).ifPresent(this::remove);
        var chat = chatFactory.createChat(id, type);
        pool.put(id, chat);
        return chat;
    }

    private void remove(Chat chat) {
        pool.remove(chat.getChatId());
    }

    private Optional<Chat> getFromPool(Long id){
        return Optional.ofNullable(pool.get(id));
    }

}
