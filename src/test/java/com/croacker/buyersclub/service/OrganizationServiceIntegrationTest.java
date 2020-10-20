package com.croacker.buyersclub.service;

import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("integration-test")
public class OrganizationServiceIntegrationTest {

    @Autowired
    private OrganizationService service;

    @Test
    @Order(1)
    void shouldInsertTwoOrganizations() {
        var addDto = new AddOrganizationDto()
                .setName("Organization 1")
                .setInn("000000001");
        var dto1 = service.save(addDto);
        addDto = new AddOrganizationDto()
                .setName("Organization 2")
                .setInn("000000002");
        var dto2 = service.save(addDto);
        addDto = new AddOrganizationDto()
                .setName("Organization 3")
                .setInn("000000003");
        var dto3 = service.save(addDto);
        assertEquals("Organization 1", dto1.getName());
        assertEquals("000000001", dto1.getInn());
        assertEquals("Organization 2", dto2.getName());
        assertEquals("000000002", dto2.getInn());
        assertEquals("Organization 3", dto3.getName());
        assertEquals("000000003", dto3.getInn());
    }

    @Test
    @Order(2)
    void shouldUpdateOrganization() {
        var updateDto = new OrganizationDto()
                .setId(1L)
                .setName("Organization 4")
                .setInn("000000004")
                .setDeleted(true);
        var dto = service.update(updateDto);
        assertEquals("Organization 3", dto.getName());
        assertEquals("000000003", dto.getInn());
        assertEquals(true, dto.getDeleted());
    }

    @Test
    @Order(3)
    void shouldGetAllOrganization() {
        var pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "createdAt");
        var all = service.findAll(pageable);
        assertEquals(3, all.size());
    }
}
