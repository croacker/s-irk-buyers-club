package com.croacker.buyersclub.client;

import com.croacker.buyersclub.config.TelegramConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class TelegramWebClientTest {

    @MockBean
    private WebClient client;

    @MockBean
    private TelegramConfiguration configuration;

    private TelegramWebClient telegramWebClient;

    @BeforeEach
    void setup() {
        telegramWebClient = new TelegramWebClient(client, configuration);
    }

    void shouldReturnFileContent(){
        // given
        var fileId = "test_file_id";
        // when and then
//        StepVerifier
//                .create(telegramWebClient.getFileContent(fileId))
    }

}