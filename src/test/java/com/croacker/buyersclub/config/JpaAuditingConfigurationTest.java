package com.croacker.buyersclub.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaAuditingConfigurationTest {

    @Test
    void shouldReturnAuditor(){
        // given
        var configuration = new JpaAuditingConfiguration();
        var expected = "user";
        // when
        var auditor = configuration.auditorProvider();
        // then
        assertNotNull(auditor);
        assertNotNull(auditor.getCurrentAuditor());
        assertTrue(auditor.getCurrentAuditor().isPresent());
        assertEquals(auditor.getCurrentAuditor().get(), expected);
    }

}