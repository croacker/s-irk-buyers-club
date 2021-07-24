package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.repo.ProductGroupRepo;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.buyersclub.service.mapper.productgroup.AddDtoToProductGroup;
import com.croacker.buyersclub.service.mapper.productgroup.DtoToProductGroup;
import com.croacker.buyersclub.service.mapper.productgroup.ProductGroupToDto;
import com.croacker.tests.TestEntitiesProducer;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductGroupServiceTest {

    private ProductGroupService service;

    @MockBean
    private ProductGroupRepo repo;

    private ProductGroupToDto toDtoMapper;

    private DtoToProductGroup toEntityMapper;

    private AddDtoToProductGroup addToEntityMapper;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        toDtoMapper = new ProductGroupToDto();
        toEntityMapper = new DtoToProductGroup();
        addToEntityMapper = new AddDtoToProductGroup();
        service = new ProductGroupServiceImpl(repo, toDtoMapper, toEntityMapper, addToEntityMapper);
    }

    @Test
    void shouldFindAll() {
        // given
        var pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findAll()).thenReturn(createEntitiesList());
        var expected = createDtosList();

        // when
        var actual = service.findAll(pageable);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindOne() {
        // given
        when(repo.findById(1L)).thenReturn(Optional.of(createEntitiy(1L)));
        var expected = createDto(1L);

        // when
        var actual = service.findOne(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldSave() {
        // given
        var given = createAddProductGroupDto(1L);
        when(repo.save(any())).thenReturn(createEntitiy(1L));
        var expected = createDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldUpdate() {
        // given
        var given = createDto(1L);
        when(repo.save(any())).thenReturn(createEntitiy(1L));
        var expected = createDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldDelete() {
        // given
        var given = createDto(1L);
        when(repo.findById(any())).thenReturn(Optional.of(createEntitiy(1L)));
        when(repo.save(any())).thenReturn(createEntitiy(1L).setDeleted(true));
        var expected = createDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<ProductGroup> createEntitiesList() {
        return Arrays.asList(
                createEntitiy(1L),
                createEntitiy(2L),
                createEntitiy(3L),
                createEntitiy(4L),
                createEntitiy(5L)
        );
    }

    private ProductGroup createEntitiy(long id) {
        return testEntitiesProducer.createProductGroup(id);
    }

    private List<ProductGroupDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private ProductGroupDto createDto(long id) {
        return testEntitiesProducer.createProductGroupDto(id);
    }

    private AddProductGroupDto createAddProductGroupDto(long id) {
        return testEntitiesProducer.createAddProductGroupDto(id);
    }
}