package com.croacker.buyersclub.service.mapper.cashier;

import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CashierToDtoMapper implements Mapper<Cashier, CashierDto> {

    @Override
    public CashierDto map(Cashier input) {
        return new CashierDto()
                .setId(input.getId())
                .setName(input.getName())
                .setShopId(input.getShop().getId())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}
