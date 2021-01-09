package com.croacker.buyersclub.telegram.chat;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ChatPoolImpl implements ChatPool{

    private final ChatFactory chatFactory;

    private Map<Long, Chat> pool;

    @Override
    public Chat getChat(Long id, String type) {
        return null;
    }

}
