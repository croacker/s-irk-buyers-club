package com.croacker.buyersclub.service.format;

/**
 * Сервис для работы с числовыми значениями
 */
public interface NumberService {

    String toCurrency(Integer val);

    String toCurrency(Long val);

}
