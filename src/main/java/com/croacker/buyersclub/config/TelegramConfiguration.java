package com.croacker.buyersclub.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.telegram")
public class TelegramConfiguration {

    private String token;

    private String username;

}
