package com.croacker.buyersclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
@Configuration

public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware auditorProvider() {
        return () -> Optional.of("user");//TODO concrete user
    }

}
