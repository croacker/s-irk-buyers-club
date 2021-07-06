package com.croacker.buyersclub.client;

import com.croacker.buyersclub.config.TelegramConfiguration;
import com.croacker.buyersclub.telegram.file.FileInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

/**
 * Клиент для обращения к Telegram по http
 */
@Service
@Slf4j
@AllArgsConstructor
public class TelegramWebClient {

    private static final String API_TELEGRAM_BOT = "https://api.telegram.org/bot";

    private static final String API_TELEGRAM_FILE = "https://api.telegram.org/file/bot";

    private final WebClient client;

    private final TelegramConfiguration configuration;

    private String getBotToken() {
        return configuration.getToken();
    }

    public Mono<String> getFileContent(String fileId) {
        return getFilePath(fileId).flatMapMany(this::getFile)
                .collect(Collectors.joining(""));
    }

    private Mono<String> getFilePath(String fileId) {
        var url = API_TELEGRAM_BOT + getBotToken() + "/getFile?file_id=" + fileId;
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(FileInfo.class)
                .map(fileInfo -> fileInfo.getResult().getFilePath());
    }

    /**
     * Получить json ОФД чеков из файла.
     *
     * @param filePath
     * @return
     */
    private Flux<String> getFile(String filePath) {
        var url = API_TELEGRAM_FILE + getBotToken() + "/" + filePath;
        log.info("File url:{}", url);
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(String.class);
    }

}
