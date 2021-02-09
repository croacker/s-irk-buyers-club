package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.repo.ProductGroupRepo;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.buyersclub.service.mapper.productgroup.AddDtoToProductGroup;
import com.croacker.buyersclub.service.mapper.productgroup.DtoToProductGroup;
import com.croacker.buyersclub.service.mapper.productgroup.ProductGroupToDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.w3c.dom.stylesheets.LinkStyle;

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
        when(repo.findAll()).thenReturn(createProductGroups());
        var expected = createProductGroupDtos();

        // when
        var actual = service.findAll(pageable);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindOne() {
        // given
        when(repo.findById(1L)).thenReturn(Optional.of(createProductGroup(1L)));
        var expected = createProductGroupDto(1L);

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
        when(repo.save(any())).thenReturn(createProductGroup(1L));
        var expected = createProductGroupDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldUpdate() {
        // given
        var given = createProductGroupDto(1L);
        when(repo.save(any())).thenReturn(createProductGroup(1L));
        var expected = createProductGroupDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldDelete() {
        // given
        var given = createProductGroupDto(1L);
        when(repo.findById(any())).thenReturn(Optional.of(createProductGroup(1L)));
        when(repo.save(any())).thenReturn(createProductGroup(1L).setDeleted(true));
        var expected = createProductGroupDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<ProductGroup> createProductGroups() {
        return Arrays.asList(
                createProductGroup(1L),
                createProductGroup(2L),
                createProductGroup(3L),
                createProductGroup(4L),
                createProductGroup(5L)
        );
    }

    private ProductGroup createProductGroup(long id) {
        return new ProductGroup()
                .setId(id)
                .setName("test_product_group_" + id)
                .setDeleted(false);
    }

    private List<ProductGroupDto> createProductGroupDtos() {
        return Arrays.asList(
                createProductGroupDto(1L),
                createProductGroupDto(2L),
                createProductGroupDto(3L),
                createProductGroupDto(4L),
                createProductGroupDto(5L)
        );
    }

    private ProductGroupDto createProductGroupDto(long id) {
        return new ProductGroupDto()
                .setId(id)
                .setName("test_product_group_" + id)
                .setDeleted(false);
    }

    private AddProductGroupDto createAddProductGroupDto(long id) {
        return new AddProductGroupDto()
                .setName("test_product_group_" + id);
    }
}