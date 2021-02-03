package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.repo.CashierRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.mapper.cashier.AddDtoToCashier;
import com.croacker.buyersclub.service.mapper.cashier.CashierToDto;
import com.croacker.buyersclub.service.mapper.cashier.DtoToCashier;
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
class CashierServiceImplTest {

    private CashierService service;

    @MockBean
    private CashierRepo repo;

    @MockBean
    private ShopRepo shopRepo;

    private CashierToDto toDtoMapper;

    private DtoToCashier toEntityMapper;

    private AddDtoToCashier addToEntityMapper;

    @BeforeEach
    void setup(){
        toDtoMapper = new CashierToDto();
        toEntityMapper = new DtoToCashier();
        addToEntityMapper = new AddDtoToCashier();
        service = new CashierServiceImpl(repo, shopRepo, toDtoMapper, toEntityMapper, addToEntityMapper);
    }

    @Test
    void shouldFindAll(){
        // given
        var given = PageRequest.of(0, 20, Sort.Direction.ASC, "createdAt");
        var cashiers = createCahiersList();
        when(repo.findByDeletedIsFalse(given)).thenReturn(cashiers);
        var expected = createCahierDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindOne(){
        // given
        var given = 1L;
        var cashier = createCashier(1L);
        when(repo.findById(given)).thenReturn(Optional.of(cashier));
        var expected = createCashierDto(1L);

        // when
        var actual = service.findOne(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindByName(){
        // given
        var given = "test_cashier_1";
        var cashier = createCashier(1L);
        when(repo.findByName(given)).thenReturn(Optional.of(cashier));
        var expected = createCashierDto(1L);

        // when
        var actual = service.findByName(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldSave(){
        // given
        var given = createAddCashierDto(1L);
        var cashier = createCashier(1L);
        var shop = createShop(1L);
        when(repo.save(any())).thenReturn(cashier);
        when(shopRepo.findById(2L)).thenReturn(Optional.of(shop));
        var expected = createCashierDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldUpdate(){
        // given
        var given = createCashierDto(1L);
        var cashier = createCashier(1L);
        var shop = createShop(1L);
        when(repo.save(any())).thenReturn(cashier);
        when(shopRepo.findById(2L)).thenReturn(Optional.of(shop));
        var expected = createCashierDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldDelete(){
        // given
        var cashier = createCashier(1L);
        var deletedCashier = createCashier(1L).setDeleted(true);
        when(repo.findById(1L)).thenReturn(Optional.of(cashier));
        when(repo.save(any())).thenReturn(deletedCashier);
        var expected = createCashierDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<Cashier> createCahiersList() {
        return Arrays.asList(
                createCashier(1L),
                createCashier(2L),
                createCashier(3L),
                createCashier(4L),
                createCashier(5L)
        );
    }

    private List<CashierDto> createCahierDtosList() {
        return Arrays.asList(
                createCashierDto(1L),
                createCashierDto(2L),
                createCashierDto(3L),
                createCashierDto(4L),
                createCashierDto(5L)
        );
    }

    private Cashier createCashier(long id){
        return new Cashier()
                .setId(id)
                .setName("test_cashier_" + id)
                .setShop(new Shop().setId(2L));
    }

    private CashierDto createCashierDto(long id){
        return new CashierDto().setId(id).setName("test_cashier_" + id).setShopId(2L);
    }

    private AddCashierDto createAddCashierDto(long id){
        return new AddCashierDto().setName("test_cashier_" + id).setShopId(2L);
    }

    private Shop createShop(long id){
        return new Shop().setId(id).setName("test_shop_" + id);
    }
}