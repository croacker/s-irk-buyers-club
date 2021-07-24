package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Organization;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.repo.OrganizationRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.dto.organization.OrganizationDto;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.shop.AddDtoToShop;
import com.croacker.buyersclub.service.mapper.shop.DtoToShop;
import com.croacker.buyersclub.service.mapper.shop.ShopToDto;
import com.croacker.tests.TestEntitiesProducer;
import liquibase.pro.packaged.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ShopServiceImplTest {

    private ShopService service;

    @MockBean
    private ShopRepo repo;

    @MockBean
    private OrganizationRepo organizationRepo;

    private ShopToDto toDtoMapper;

    private DtoToShop toShopMapper;

    private AddDtoToShop addToEntityMapper;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        toDtoMapper = new ShopToDto();
        toShopMapper = new DtoToShop();
        addToEntityMapper = new AddDtoToShop();
        service = new ShopServiceImpl(repo, organizationRepo,toDtoMapper, toShopMapper, addToEntityMapper);
    }

    @Test
    void shouldFindAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByDeletedIsFalse(given)).thenReturn(createEntitiesList());
        var expected = createDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findOne() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findByAddress() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getShops() {
    }

    private List<Shop> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private Shop createEntity(long id) {
        return testEntitiesProducer.createShop(id);
    }

    private List<ShopDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private ShopDto createDto(long id) {
        return testEntitiesProducer.createShopDto(id);
    }

}