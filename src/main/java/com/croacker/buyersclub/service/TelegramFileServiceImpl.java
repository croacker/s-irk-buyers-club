package com.croacker.buyersclub.service;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.telegram.file.FileInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TelegramFileServiceImpl implements TelegramFileService {

    private final TelegramConfiguration configuration;

    private final WebClient client;

    private final OfdCheckServiceImpl ofdCheckService;

    @Override
    public void processFile(Message message) {
        getFileId(message).ifPresent(this::processFile);
    }

    private String processFile(String fileId) {
        Mono<String> filePath = getFilePath(fileId);

        filePath.map(path -> {
            getFile(path).subscribe();
            return path;
        }).subscribe();
        return StringUtils.EMPTY;
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
     * Получить ОФД чеки из файла.
     *
     * @param filePath
     * @return
     */
    private Flux<List<OfdCheck>> getFile(String filePath) {
        var url = "https://api.telegram.org/file/bot" + getBotToken() + "/" + filePath;
        log.info("File url:{}", url);
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(String.class)
                .map(str -> {// TODO если json отформатирован, он будет поступать построчно и не может быть десериализован
                    List<OfdCheck> ofdChecks = Collections.EMPTY_LIST;
                    var objectMapper = new ObjectMapper();
                    try {
                        ofdChecks = objectMapper.readValue(str, new TypeReference<>() {
                        });
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage(), e);
                    }
                    log.info("OfdChecks:{}", ofdChecks);
                    ofdChecks.forEach(ofdCheck -> ofdCheckService.process(ofdCheck));
                    return ofdChecks;
                });
    }

    public String getBotToken() {
        return configuration.getToken();
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

    private Mono<String> getFilePath(String fileId) {
        var url = "https://api.telegram.org/bot" + getBotToken() + "/getFile?file_id=" + fileId;
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(FileInfo.class)
                .map(fileInfo -> fileInfo.getResult().getFilePath());
    }

}
