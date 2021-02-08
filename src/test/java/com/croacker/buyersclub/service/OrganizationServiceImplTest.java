package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.repo.OrganizationRepo;
import com.croacker.buyersclub.service.mapper.organization.AddDtoToOrganization;
import com.croacker.buyersclub.service.mapper.organization.DtoToOrganization;
import com.croacker.buyersclub.service.mapper.organization.OrganizationToDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.org.apache.commons.lang.NotImplementedException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class OrganizationServiceImplTest {

    private OrganizationService service;

    @MockBean
    private OrganizationRepo repo;

    private OrganizationToDto toDtoMapper;

    private DtoToOrganization toEntityMapper;

    private AddDtoToOrganization addToEntityMapper;

    void setup() {
        toDtoMapper = new OrganizationToDto();
        toEntityMapper = new DtoToOrganization();
        addToEntityMapper = new AddDtoToOrganization();
        service = new OrganizationServiceImpl(repo, toDtoMapper, toEntityMapper, addToEntityMapper);
    }

    @Test
    void shouldFindAll() {
        // given

        // when

        // then
        throw new NotImplementedException();
    }

    @Test
    void shouldFindOne() {
        // given

        // when

        // then
        throw new NotImplementedException();
    }

    @Test
    void shouldFindByInn() {
        // given

        // when

        // then
        throw new NotImplementedException();
    }

    @Test
    void shouldGetOrganizations() {
        // given

        // when

        // then
        throw new NotImplementedException();
    }

    @Test
    void shouldSave() {
        // given

        // when

        // then
        throw new NotImplementedException();
    }

    @Test
    void shouldUpdate() {
        // given

        // when

        // then
        throw new NotImplementedException();
    }

    @Test
    void shouldDelete() {
        // given

        // when

        // then
        throw new NotImplementedException();
    }
}