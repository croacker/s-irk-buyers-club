package com.croacker.buyersclub.service.mapper.cashier;

import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToCashierMapper implements Mapper<CashierDto, Cashier> {

    @Override
    public Cashier map(CashierDto input) {
        return new Cashier()
                .setId(input.getId())
                .setName(input.getName())
                .setDeleted(input.getDeleted());
    }

}
