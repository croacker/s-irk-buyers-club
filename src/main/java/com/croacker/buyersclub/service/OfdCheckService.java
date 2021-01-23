package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.ofd.OfdCheck;

/**
 * Сервис обработки поступающих чеков ОФД.
 */
public interface OfdCheckService {

    TelegramFileProcessResult process(OfdCheck ofdCheck, Long userId);

}
