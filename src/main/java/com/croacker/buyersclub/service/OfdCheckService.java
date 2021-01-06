package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.ofd.OfdCheck;

/**
 * Сервис обработки поступающих чеков ОФД.
 */
public interface OfdCheckService {

    void process(OfdCheck ofdCheck, Long userId);

}
