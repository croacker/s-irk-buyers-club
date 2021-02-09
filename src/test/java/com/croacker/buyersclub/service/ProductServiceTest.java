package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductGroup;
import com.croacker.buyersclub.repo.ProductGroupRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.service.dto.product.AddProductDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productgroup.AddProductGroupDto;
import com.croacker.buyersclub.service.dto.productgroup.ProductGroupDto;
import com.croacker.buyersclub.service.mapper.product.AddDtoToProduct;
import com.croacker.buyersclub.service.mapper.product.DtoToProduct;
import com.croacker.buyersclub.service.mapper.product.ProductToDto;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductServiceTest {

    private ProductService service;

    @MockBean
    private ProductRepo repo;

    @MockBean
    private ProductGroupRepo productGroupRepo;

    private ProductToDto toDtoMapper;

    private DtoToProduct toEntityMapper;

    private AddDtoToProduct addToEntityMapper;

    @BeforeEach
    void setup(){
        toDtoMapper = new ProductToDto();
        toEntityMapper = new DtoToProduct();
        addToEntityMapper = new AddDtoToProduct();
        service = new ProductServiceImpl(repo, productGroupRepo, toDtoMapper, toEntityMapper, addToEntityMapper);
    }

    @Test
    void shouldFindAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByDeletedIsFalse(given)).thenReturn(createProducts());
        var expected = createProductDtos();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindOne() {
        // given
        when(repo.findById(1L)).thenReturn(Optional.of(createProduct(1L)));
        var expected = createProductDto(1L);

        // when
        var actual = service.findOne(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldSave() {
        // given
        var given = createAddProductDto(1L);
        when(repo.save(any())).thenReturn(createProduct(1L));
        when(productGroupRepo.findById(1L)).thenReturn(Optional.of(createProductGroup(1L)));
        var expected = createProductDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldUpdate() {
        // given
        var given = createProductDto(1L);
        when(repo.save(any())).thenReturn(createProduct(1L));
        when(productGroupRepo.findById(1L)).thenReturn(Optional.of(createProductGroup(1L)));
        var expected = createProductDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldDelete() {
        // given
        var given = createProductDto(1L);
        when(repo.findById(any())).thenReturn(Optional.of(createProduct(1L)));
        when(repo.save(any())).thenReturn(createProduct(1L).setDeleted(true));
        var expected = createProductDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<Product> createProducts() {
        return Arrays.asList(
                createProduct(1L),
                createProduct(2L),
                createProduct(3L),
                createProduct(4L),
                createProduct(5L)
        );
    }

    private Product createProduct(long id) {
        return new Product()
                .setId(id)
                .setProductGroup(createProductGroup(id))
                .setName("test_product_group_" + id)
                .setDeleted(false);
    }

    private List<ProductDto> createProductDtos() {
        return Arrays.asList(
                createProductDto(1L),
                createProductDto(2L),
                createProductDto(3L),
                createProductDto(4L),
                createProductDto(5L)
        );
    }

    private ProductDto createProductDto(long id) {
        return new ProductDto()
                .setId(id)
                .setProductGroupId(id)
                .setName("test_product_group_" + id)
                .setDeleted(false);
    }

    private AddProductDto createAddProductDto(long id) {
        return new AddProductDto()
                .setName("test_product_group_" + id)
                .setProductGroupId(id);
    }

    private ProductGroup createProductGroup(long id) {
        return new ProductGroup()
                .setId(id)
                .setName("test_product_group_" + id)
                .setDeleted(false);
    }
}