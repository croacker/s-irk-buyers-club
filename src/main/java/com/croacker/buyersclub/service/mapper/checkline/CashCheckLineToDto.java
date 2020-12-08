package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CashCheckLineToDto implements Mapper<CashCheckLine, CashCheckLineDto> {
    @Override
    public CashCheckLineDto map(CashCheckLine input) {
        return new CashCheckLineDto()
                .setId(input.getId())
                .setProductId(input.getProduct().getId())
                .setPrice(input.getPrice())
                .setQuantity(input.getQuantity())
                .setTotalSum(input.getTotalSum());
    }
}
