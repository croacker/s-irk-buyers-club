package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.CashCheck;
import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.domain.TelegramUser;
import com.croacker.buyersclub.repo.CashierRepo;
import com.croacker.buyersclub.repo.CheckRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.repo.TelegramUserRepo;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import com.croacker.buyersclub.service.mapper.check.AddDtoToCashCheck;
import com.croacker.buyersclub.service.mapper.check.CashCheckToDto;
import com.croacker.buyersclub.service.mapper.check.CashCheckToInfoDto;
import com.croacker.buyersclub.service.mapper.check.DtoToCashCheck;
import com.croacker.buyersclub.service.mapper.checkline.AddDtoToCashCheckLine;
import com.croacker.buyersclub.service.mapper.checkline.CashCheckLineToInfoDto;
import com.croacker.buyersclub.service.mapper.telegramuser.TelegramUserToDto;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class CheckServiceTest {

    private final static LocalDateTime NOW = LocalDateTime.now();

    private CheckService service;

    @MockBean
    private CheckRepo repo;

    @MockBean
    private CashierRepo cashierRepo;

    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private TelegramUserRepo telegramUserRepo;

    private CashCheckToDto toDtoMapper;

    private CashCheckToInfoDto toInfoDtoMapper;

    private DtoToCashCheck toEntityMapper;

    private AddDtoToCashCheck addToEntityMapper;

    private AddDtoToCashCheckLine addLineToEntityMapper;

    private CashCheckLineToInfoDto lineMapper;

    private TelegramUserToDto telegramUserToDto;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup() {
        toDtoMapper = new CashCheckToDto();
        lineMapper = new CashCheckLineToInfoDto();
        telegramUserToDto = new TelegramUserToDto();
        toInfoDtoMapper = new CashCheckToInfoDto(lineMapper, telegramUserToDto);
        toEntityMapper = new DtoToCashCheck();
        addToEntityMapper = new AddDtoToCashCheck();
        addLineToEntityMapper = new AddDtoToCashCheckLine();
        service = new CheckServiceImpl(repo, cashierRepo, productRepo,
                telegramUserRepo, toDtoMapper, toInfoDtoMapper,
                toEntityMapper, addToEntityMapper, addLineToEntityMapper);
    }

    @Test
    void findAll() {
        // given
        var given = PageRequest.of(0, 20, Sort.Direction.ASC, "createdAt");
        var checks = createEntitiesList();
        when(repo.findByDeletedIsFalse(given)).thenReturn(checks);
        var expected = createInfoDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findOne() {
        // given
        var given = 0L;
        var check = createEntity(0L);
        when(repo.findById(given)).thenReturn(Optional.of(check));
        var expected = createInfoDto(0L);

        // when
        var actual = service.findById(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void save() {
        // given
        var given = createAddDto(0L);
        var check = createEntity(0L);
        var cashier = createCashier(0L);
        var telegramUser = createTelegramUser(0L);
        when(repo.save(any())).thenReturn(check);
        when(cashierRepo.findById(0L)).thenReturn(Optional.of(cashier));
        when(telegramUserRepo.findById(0L)).thenReturn(Optional.of(telegramUser));
        var expected = createDto(0L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void update() {
        // given
        var given = createDto(0L);
        var check = createEntity(0L);
        var cashier = createCashier(0L);
        var telegramUser = createTelegramUser(0L);
        when(repo.save(any())).thenReturn(check);
        when(cashierRepo.findById(0L)).thenReturn(Optional.of(cashier));
        when(telegramUserRepo.findById(0L)).thenReturn(Optional.of(telegramUser));
        var expected = createDto(0L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void delete() {
        // given
        var check = createEntity(0L);
        var deletedCheck = createEntity(0L).setDeleted(true);
        when(repo.findById(0L)).thenReturn(Optional.of(check));
        when(repo.save(any())).thenReturn(deletedCheck);
        var expected = createDto(0L).setDeleted(true);

        // when
        var actual = service.delete(0L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<CashCheck> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private List<CashCheckInfoDto> createInfoDtosList() {
        return Arrays.asList(
                createInfoDto(1L),
                createInfoDto(2L),
                createInfoDto(3L),
                createInfoDto(4L),
                createInfoDto(5L)
        );
    }

    private CashCheck createEntity(long id) {
        return testEntitiesProducer.createCashCheck(id);
    }

    private CashCheckInfoDto createInfoDto(long id) {
        return testEntitiesProducer.createCashCheckInfoDto(id);
    }

    private CashCheckDto createDto(long id) {
        return testEntitiesProducer.createCashCheckDto(id);
    }

    private AddCashCheckDto createAddDto(long id) {
        return testEntitiesProducer.createAddCashCheckDto(id);
    }

    private Cashier createCashier(long id) {
        return new Cashier().setId(id);
    }

    private TelegramUser createTelegramUser(long id) {
        return new TelegramUser().setId(id);
    }
}