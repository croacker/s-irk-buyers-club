package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.TestConfiguration;
import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import com.croacker.buyersclub.service.ofd.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class ItemToAddCheckLineDtoTest {


    private ItemToAddCheckLineDto mapper;

    @BeforeEach
    void setup() {
        mapper = new ItemToAddCheckLineDto();
    }

    @Test
    void shouldMapDto() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Item createEntity() {
        return new Item()
                .setPrice(100)
                .setQuantity(2)
                .setSum(200);
    }

    private AddCashCheckLineDto createDto() {
        return new AddCashCheckLineDto()
                .setPrice(100)
                .setQuantity(2000)
                .setTotalSum(200);
    }


}
