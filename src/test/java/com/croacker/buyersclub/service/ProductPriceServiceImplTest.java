package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.repo.ProductPriceRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.mapper.productprice.AddDtoToProductPrice;
import com.croacker.buyersclub.service.mapper.productprice.DtoToProductPrice;
import com.croacker.buyersclub.service.mapper.productprice.ProductPriceToDto;
import com.croacker.buyersclub.service.mapper.productprice.ProductPriceToInfoDto;
import com.croacker.buyersclub.service.mapper.telegram.ProductPriceToTelegramDto;
import com.croacker.tests.TestEntitiesProducer;
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

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductPriceServiceImplTest {

    public static final LocalDateTime NOW = LocalDateTime.now();

    private ProductPriceServiceImpl service;

    @MockBean
    private ProductPriceRepo repo;

    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private ShopRepo shopRepo;

    private ProductPriceToDto toDtoMapper;

    private ProductPriceToInfoDto toInfoDtoMapper;

    private DtoToProductPrice toEntityMapper;

    private AddDtoToProductPrice addToEntityMapper;

    private ProductPriceToTelegramDto toTelegramDtoMapper;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        toDtoMapper = new ProductPriceToDto();
        toInfoDtoMapper = new ProductPriceToInfoDto();
        toEntityMapper = new DtoToProductPrice();
        toTelegramDtoMapper = new ProductPriceToTelegramDto();
        service = new ProductPriceServiceImpl(repo, productRepo, shopRepo, toDtoMapper, toInfoDtoMapper,
                toEntityMapper, addToEntityMapper, toTelegramDtoMapper);
    }


    @Test
    void findAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByDeletedIsFalse(given)).thenReturn(createEntitiesList());

        // when
        var actual = service.findAll(given);


    }

    @Test
    void findOne() {
    }

    @Test
    void findByProduct() {
    }

    @Test
    void findPrice() {
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
    void getProductsPrices() {
    }

    private List<ProductPrice> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private ProductPrice createEntity(long id) {
        return testEntitiesProducer.createProductPrice(id);
    }

}