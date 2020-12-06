package com.croacker.buyersclub.service.mapper.cashier;

import com.croacker.buyersclub.domain.Cashier;
import com.croacker.buyersclub.service.dto.cashier.AddCashierDto;
import com.croacker.buyersclub.service.dto.cashier.CashierDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToCashierMapper implements Mapper<AddCashierDto, Cashier> {

    @Override
    public Cashier map(AddCashierDto input) {
        return new Cashier()
                .setName(input.getName());
    }

}
