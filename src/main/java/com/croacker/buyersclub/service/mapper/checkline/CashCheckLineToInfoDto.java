package com.croacker.buyersclub.service.mapper.checkline;

import com.croacker.buyersclub.domain.CashCheckLine;
import com.croacker.buyersclub.service.dto.checkline.CashCheckLineInfoDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CashCheckLineToInfoDto implements Mapper<CashCheckLine, CashCheckLineInfoDto> {
    @Override
    public CashCheckLineInfoDto map(CashCheckLine input) {
        return new CashCheckLineInfoDto()
                .setId(input.getId())
                .setProductId(input.getProduct().getId())
                .setProductName(input.getProduct().getName())
                .setPrice(input.getPrice())
                .setQuantity(input.getQuantity())
                .setTotalSum(input.getTotalSum());
    }
}
