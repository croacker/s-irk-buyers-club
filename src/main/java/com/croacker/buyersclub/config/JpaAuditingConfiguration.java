package com.croacker.buyersclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware auditorProvider() {
        return () -> Optional.of("user");//TODO concrete user
    }
}
