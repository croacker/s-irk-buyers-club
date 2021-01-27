package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import com.croacker.buyersclub.service.ofd.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemToAddCheckLineDto implements Mapper<Item, AddCashCheckLineDto> {
    @Override
    public AddCashCheckLineDto map(Item input) {
        return new AddCashCheckLineDto()
                .setPrice(input.getPrice())
                .setQuantity(toInteger(input.getQuantity()))
                .setTotalSum(input.getSum());
    }

    private Integer toInteger(float quantity) {
        return Math.round(quantity * 1000);
    }
}
