package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.repo.CashierRepo;
import com.croacker.buyersclub.repo.CheckRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.repo.TelegramUserRepo;
import com.croacker.buyersclub.service.mapper.check.AddDtoToCashCheck;
import com.croacker.buyersclub.service.mapper.check.CashCheckToDto;
import com.croacker.buyersclub.service.mapper.check.CashCheckToInfoDto;
import com.croacker.buyersclub.service.mapper.check.DtoToCashCheck;
import com.croacker.buyersclub.service.mapper.checkline.AddDtoToCashCheckLine;
import com.croacker.buyersclub.service.mapper.checkline.CashCheckLineToInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class CheckServiceImplTest {

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

    @BeforeEach
    void setup() {
        toDtoMapper = new CashCheckToDto();
        lineMapper = new CashCheckLineToInfoDto();
        toInfoDtoMapper = new CashCheckToInfoDto(lineMapper);
        toEntityMapper = new DtoToCashCheck();
        addToEntityMapper = new AddDtoToCashCheck();
        addLineToEntityMapper = new AddDtoToCashCheckLine();
        service = new CheckServiceImpl(repo, cashierRepo, productRepo,
                telegramUserRepo, toDtoMapper, toInfoDtoMapper,
                toEntityMapper, addToEntityMapper, addLineToEntityMapper);
    }

    @Test
    void shouldFindAll() {
    }

    @Test
    void shouldFindOne() {
    }

    @Test
    void shouldSave() {
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void shouldDelete() {
    }
}