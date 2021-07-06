package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.repo.OrganizationRepo;
import com.croacker.buyersclub.service.dto.organization.AddOrganizationDto;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.mapper.organization.AddDtoToOrganization;
import com.croacker.buyersclub.service.mapper.organization.DtoToOrganization;
import com.croacker.buyersclub.service.mapper.organization.OrganizationToDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class OrganizationServiceTest {

    private OrganizationService service;

    @MockBean
    private OrganizationRepo repo;

    private OrganizationToDto toDtoMapper;

    private DtoToOrganization toEntityMapper;

    private AddDtoToOrganization addToEntityMapper;

    @BeforeEach
    void setup() {
        toDtoMapper = new OrganizationToDto();
        toEntityMapper = new DtoToOrganization();
        addToEntityMapper = new AddDtoToOrganization();
        service = new OrganizationServiceImpl(repo, toDtoMapper, toEntityMapper, addToEntityMapper);
    }

    @Test
    void shouldFindAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByDeletedIsFalse(given)).thenReturn(createOrganizations());
        var expected = createOrganizationDtos();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindOne() {
        // given
        when(repo.findById(1L)).thenReturn(Optional.of(createOrganization(1L)));
        var expected = createOrganizationDto(1L);

        // when
        var actual = service.findOne(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindByInn() {
        // given
        when(repo.findByInn("test_inn_1")).thenReturn(Optional.of(createOrganization(1L)));
        var expected = createOrganizationDto(1L);

        // when
        var actual = service.findByInn("test_inn_1");

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldGetOrganizations() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByNameContainingIgnoreCase("name", given)).thenReturn(createOrganizations());
        var expected = createOrganizationDtos();

        // when
        var actual = service.getOrganizations("name", given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldSave() {
        // given
        var given = createAddOrganizationDto(1L);
        when(repo.save(any())).thenReturn(createOrganization(1L));
        var expected = createOrganizationDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldUpdate() {
        // given
        var given = createOrganizationDto(1L);
        when(repo.save(any())).thenReturn(createOrganization(1L));
        var expected = createOrganizationDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldDelete() {
        // given
        var given = createOrganizationDto(1L);
        when(repo.findById(any())).thenReturn(Optional.of(createOrganization(1L)));
        when(repo.save(any())).thenReturn(createOrganization(1L).setDeleted(true));
        var expected = createOrganizationDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<Organization> createOrganizations() {
        return Arrays.asList(
                createOrganization(1L),
                createOrganization(2L),
                createOrganization(3L),
                createOrganization(4L),
                createOrganization(5L)
        );
    }

    private List<OrganizationDto> createOrganizationDtos() {
        return Arrays.asList(
                createOrganizationDto(1L),
                createOrganizationDto(2L),
                createOrganizationDto(3L),
                createOrganizationDto(4L),
                createOrganizationDto(5L)
        );
    }

    private Organization createOrganization(long id) {
        return new Organization()
                .setId(id)
                .setName("test_name_" + id)
                .setInn("test_inn_" + id);
    }

    private OrganizationDto createOrganizationDto(long id) {
        return new OrganizationDto()
                .setId(id)
                .setName("test_name_" + id)
                .setInn("test_inn_" + id);
    }

    private AddOrganizationDto createAddOrganizationDto(long id) {
        return new AddOrganizationDto()
                .setName("test_name_" + id)
                .setInn("test_inn_" + id);
    }
}