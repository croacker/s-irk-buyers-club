package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.service.dto.checkline.AddCashCheckLineDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToCashCheckLine implements Mapper<AddCashCheckLineDto, CashCheckLine> {

    @Override
    public CashCheckLine map(AddCashCheckLineDto input) {
        return new CashCheckLine()
                .setPrice(input.getPrice())
                .setQuantity(input.getQuantity())
                .setTotalSum(input.getTotalSum());
    }

}
