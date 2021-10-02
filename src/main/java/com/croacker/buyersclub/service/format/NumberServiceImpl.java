package com.croacker.buyersclub.service.format;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class NumberServiceImpl implements NumberService{

    @Override
    public String toCurrency(Integer val) {
        var price = val.doubleValue() /100d;
        return String.format(Locale.ROOT, "%10.2f", price).trim();
    }

    @Override
    public String toCurrency(Long val) {
        var price = val.doubleValue() /100d;
        return String.format(Locale.ROOT, "%10.2f", price).trim();
    }
}
