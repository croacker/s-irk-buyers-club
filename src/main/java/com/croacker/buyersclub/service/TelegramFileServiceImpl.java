package com.croacker.buyersclub.service;

import com.croacker.buyersclub.client.TelegramWebClient;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сервис работы с файлами telegram.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TelegramFileServiceImpl implements TelegramFileService {

    private final TelegramWebClient client;

    private final OfdCheckServiceImpl ofdCheckService;

    @Override
    public void processFile(Message message) {
        getFileId(message).ifPresent(fileId -> {
            client.getFileContent(fileId)
                    .map(this::toOfdChecks)
                    .doOnNext(this::processChecks)
                    .subscribe();
        });
    }

    /**
     * Строку в набор ОФД чеков
     *
     * @param str строка
     * @return
     */
    private List<OfdCheck> toOfdChecks(String str) {
        List<OfdCheck> ofdChecks = Collections.EMPTY_LIST;
        var objectMapper = new ObjectMapper();
        try {
            ofdChecks = objectMapper.readValue(str, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        log.info("OfdChecks:{}", ofdChecks);
        return ofdChecks;
    }

    /**
     * Обработать ОФД чеки
     *
     * @param ofdChecks ОФД чеки
     */
    private void processChecks(List<OfdCheck> ofdChecks) {
        ofdChecks.forEach(ofdCheck -> ofdCheckService.process(ofdCheck));
    }

    /**
     * Получить идентификатор файла.
     *
     * @param message сообщение
     * @return идентификатор файла
     */
    private Optional<String> getFileId(Message message) {
        return getDocument(message).map(Document::getFileId);
    }

    /**
     * Получить документ.
     *
     * @param message сообщение
     * @return документ
     */
    private Optional<Document> getDocument(Message message) {
        Optional<Document> result = Optional.empty();
        if (message != null) {
            result = Optional.ofNullable(message.getDocument());
        }
        return result;
    }

}
