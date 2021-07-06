package com.croacker.buyersclub.client;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.config.TelegramConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfiguration.class })
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