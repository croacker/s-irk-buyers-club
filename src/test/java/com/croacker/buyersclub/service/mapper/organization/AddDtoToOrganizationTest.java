package com.croacker.buyersclub.service.mapper.organization;


import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class AddDtoToOrganizationTest {

    private AddDtoToOrganization mapper;

    @BeforeEach
    void setup() {
        mapper = new AddDtoToOrganization();
    }

    @Test
    void shouldMapDto() {
        //given
        var given = createDto();
        var expected = createEntity();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Organization createEntity() {
        return new Organization()
                .setName("test_organization")
                .setInn("test_inn");
    }

    private AddOrganizationDto createDto() {
        return new AddOrganizationDto()
                .setName("test_organization")
                .setInn("test_inn");
    }

}
