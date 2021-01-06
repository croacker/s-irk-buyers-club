package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.CheckService;
import com.croacker.buyersclub.service.dto.check.AddCashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckDto;
import com.croacker.buyersclub.service.dto.check.CashCheckInfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/check")
@AllArgsConstructor
@Slf4j
public class CheckController implements CheckOperations{

    private final CheckService service;

    @Override
    public Flux<CashCheckInfoDto> getAllChecks(int page, int size, String sort, Sort.Direction direction){
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<CashCheckInfoDto> getCheck(Long id) {
        return Mono.just(service.findOne(id));
    }

    @Override
    public Mono<CashCheckDto> createCheck(AddCashCheckDto dto) {
        return Mono.just(service.save(dto));
    }

    @Override
    public Mono<CashCheckDto> updateCheck(@RequestBody CashCheckDto dto){
        return Mono.just(service.update(dto));
    }

    @Override
    public Mono<CashCheckDto> deleteCheck(@PathVariable Long id){
        return Mono.just(service.delete(id));
    }

}
