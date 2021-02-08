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
import org.apache.commons.compress.archivers.dump.DumpArchiveEntry;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class CheckServiceImplTest {

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
        // given
        var given = PageRequest.of(0, 20, Sort.Direction.ASC, "createdAt");
        var checks = createChecksList();
        when(repo.findByDeletedIsFalse(given)).thenReturn(checks);
        var expected = createCheckInfoDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldFindOne() {
        // given
        var given = 1L;
        var check = createCheck(1L);
        when(repo.findById(given)).thenReturn(Optional.of(check));
        var expected = createCheckInfoDto(1L);

        // when
        var actual = service.findOne(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldSave() {
        // given
        var given = createAddCheckDto(1L);
        var check = createCheck(1L);
        var cashier = createCashier(1L);
        var telegramUser = createTelegramUser(1L);
        when(repo.save(any())).thenReturn(check);
        when(cashierRepo.findById(1L)).thenReturn(Optional.of(cashier));
        when(telegramUserRepo.findById(1L)).thenReturn(Optional.of(telegramUser));
        var expected = createCheckDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldUpdate() {
        // given
        var given = createCheckDto(1L);
        var check = createCheck(1L);
        var cashier = createCashier(1L);
        var telegramUser = createTelegramUser(1L);
        when(repo.save(any())).thenReturn(check);
        when(cashierRepo.findById(1L)).thenReturn(Optional.of(cashier));
        when(telegramUserRepo.findById(1L)).thenReturn(Optional.of(telegramUser));
        var expected = createCheckDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void shouldDelete() {
        // given
        var check = createCheck(1L);
        var deletedCheck = createCheck(1L).setDeleted(true);
        when(repo.findById(1L)).thenReturn(Optional.of(check));
        when(repo.save(any())).thenReturn(deletedCheck);
        var expected = createCheckDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<CashCheck> createChecksList() {
        return Arrays.asList(
                createCheck(1L),
                createCheck(2L),
                createCheck(3L),
                createCheck(4L),
                createCheck(5L)
        );
    }

    private List<CashCheckInfoDto> createCheckInfoDtosList() {
        return Arrays.asList(
                createCheckInfoDto(1L),
                createCheckInfoDto(2L),
                createCheckInfoDto(3L),
                createCheckInfoDto(4L),
                createCheckInfoDto(5L)
        );
    }

    private CashCheck createCheck(long id) {
        return new CashCheck()
                .setId(id)
                .setCashier(createCashier(1l))
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setCheckLines(Collections.emptyList())
                .setDeleted(false);
    }

    private CashCheckInfoDto createCheckInfoDto(long id) {
        return new CashCheckInfoDto()
                .setId(id)
                .setCashierId(1L)
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setCheckLines(Collections.emptyList())
                .setDeleted(false);
    }

    private CashCheckDto createCheckDto(long id) {
        return new CashCheckDto()
                .setId(id)
                .setCashierId(1L)
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

    private AddCashCheckDto createAddCheckDto(long id) {
        return new AddCashCheckDto()
                .setCashierId(1L)
                .setRequestNumber("test_request_number")
                .setShiftNumber("test_shift_number")
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setCheckLines(Collections.emptyList())
                .setTelegramUserId(1L);
    }

    private Cashier createCashier(long id) {
        return new Cashier().setId(id);
    }

    private TelegramUser createTelegramUser(long id) {
        return new TelegramUser().setId(id);
    }
}