package com.croacker.buyersclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import java.util.Optional;
@Configuration
@EnableR2dbcAuditing
public class JpaAuditingConfiguration {
    //TODO concrete user
//    @Bean
//    public ReactiveAuditorAware<String> auditorAware() {
//        return new AuditorAwareImpl();
//    }
}
