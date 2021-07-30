package com.croacker.buyersclub.service;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.domain.TelegramUser;
import com.croacker.buyersclub.repo.TelegramUserRepo;
import com.croacker.buyersclub.service.dto.telegramuser.AddTelegramUserDto;
import com.croacker.buyersclub.service.dto.telegramuser.TelegramUserDto;
import com.croacker.buyersclub.service.mapper.telegramuser.AddDtoToTelegramUser;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class TelegramUserServiceImplTest {

    private TelegramUserServiceImpl service;

    @MockBean
    private TelegramUserRepo repo;

    private TelegramUserToDto toDtoMapper;

    private AddDtoToTelegramUser addToEntityMapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        toDtoMapper = new TelegramUserToDto();
        addToEntityMapper = new AddDtoToTelegramUser();
        service = new TelegramUserServiceImpl(repo, toDtoMapper,addToEntityMapper);
    }

    @Test
    void findAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findAll()).thenReturn(createEntitiesList());
        var expected = createDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findOne() {
        // given
        when(repo.findById(0L)).thenReturn(Optional.of(createEntity(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.findOne(0L).get();

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findByName() {
        // given
        var given = "test_user_name_0";
        when(repo.findByUserName("test_user_name_0")).thenReturn(Optional.of(createEntity(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.findByName(given).get();

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void save() {
        // given
        var given = createAddDto(0L);
        when(repo.save(any())).thenReturn(createEntity(0L));
        var expected = createDto(0L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);    }

    private List<TelegramUser> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private TelegramUser createEntity(long id) {
        return testEntitiesProducer.createTelegramUser(id);
    }

    private List<TelegramUserDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private TelegramUserDto createDto(long id) {
        return testEntitiesProducer.createTelegramUserDto(id);
    }

    private AddTelegramUserDto createAddDto(long id) {
        return testEntitiesProducer.createAddTelegramUserDto(id);
    }
}