package com.croacker.buyersclub.service.telegram;

import com.croacker.buyersclub.client.TelegramWebClient;
import com.croacker.buyersclub.service.OfdCheckServiceImpl;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.excerpt.Excerpt;
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
import java.util.stream.Collectors;

/**
 * Сервис работы с файлами telegram.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TelegramFileServiceImpl implements TelegramFileService {

    private final TelegramWebClient client;

    private final OfdCheckServiceImpl ofdCheckService;

    private final TelegramTelegramUserServiceImpl telegramTelegramUserService;

    @Override
    public void processFile(Message message) {
        var userId = telegramTelegramUserService.saveUser(message);
        getFileId(message).ifPresent(fileId -> {
            client.getFileContent(fileId)
                    .map(this::toOfdChecks)
                    .doOnNext(ofdChecks -> processChecks(ofdChecks, userId))
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
        List<OfdCheck> ofdChecks;
        var objectMapper = new ObjectMapper();
        ofdChecks = readAsChecks(str, objectMapper);
        if (ofdChecks.isEmpty()){
            ofdChecks = readAsExcerpt(str, objectMapper);
        }
        log.info("OfdChecks:{}", ofdChecks);
        return ofdChecks;
    }

    /**
     * Прочитать как чеки.
     *
     * @param str          строка
     * @param objectMapper транслятор
     * @return чеки
     */
    private List<OfdCheck> readAsChecks(String str, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(str, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e); // TODO исключение може быть при чтении выписки как чека
            return Collections.emptyList();
        }
    }

    /**
     * Прочитать как чеки.
     *
     * @param str          строка
     * @param objectMapper транслятор
     * @return чеки
     */
    private List<OfdCheck> readAsExcerpt(String str, ObjectMapper objectMapper) {
        List<OfdCheck> ofdChecks = Collections.emptyList();
        List<Excerpt> excerpts = Collections.emptyList();
        try {
            excerpts = objectMapper.readValue(str, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        if (!excerpts.isEmpty()){
            ofdChecks = excerpts.stream()
                    .map(excerpt -> excerpt.getTicket().getDocument().getOfdCheck())
                    .collect(Collectors.toList());
        }
        return ofdChecks;
    }

    /**
     * Обработать ОФД чеки
     *
     * @param ofdChecks ОФД чеки
     * @param userId
     */
    private void processChecks(List<OfdCheck> ofdChecks, Long userId) {
        ofdChecks.forEach(ofdCheck -> ofdCheckService.process(ofdCheck, userId));
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
