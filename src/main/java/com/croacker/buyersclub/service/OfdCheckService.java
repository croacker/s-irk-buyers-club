package com.croacker.buyersclub.service;

import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramFileProcessResult;
import com.croacker.buyersclub.service.ofd.OfdCheck;
import reactor.core.publisher.Mono;

/**
 * Сервис обработки поступающих чеков ОФД.
 */
public interface OfdCheckService {

    Mono<CashCheckDto> process(OfdCheck ofdCheck, Long userId);

}
