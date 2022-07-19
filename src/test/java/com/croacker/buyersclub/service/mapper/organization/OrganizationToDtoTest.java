package com.croacker.buyersclub.service.mapper.organization;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OrganizationToDtoTest {

    @Autowired
    private OrganizationToDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Organization createEntity() {
        return new Organization()
                .setId(0L)
                .setName("test_organization")
                .setInn("test_inn")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private OrganizationDto createDto() {
        return new OrganizationDto()
                .setId(0L)
                .setName("test_organization")
                .setInn("test_inn")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }


}