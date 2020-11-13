package com.croacker.buyersclub.controller;

import com.croacker.buyersclub.service.CheckService;
import com.croacker.buyersclub.service.dto.check.AddCheckDto;
import com.croacker.buyersclub.service.dto.check.CheckDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/check")
@AllArgsConstructor
@Slf4j
public class CheckController implements CheckOperations{

    private final CheckService service;

    @Override
    public Flux<CheckDto> getAllChecks(int page, int size, String sort, Sort.Direction direction){
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<CheckDto> getCheck(Long id) {
        return Mono.just(service.findOne(id));
    }

    @Override
    public Mono<CheckDto> createCheck(AddCheckDto dto) {
        return Mono.just(service.save(dto));
    }

    @Override
    public Mono<CheckDto> updateCheck(@RequestBody CheckDto dto){
        return Mono.just(service.update(dto));
    }

    @Override
    public Mono<CheckDto> deleteCheck(@PathVariable Long id){
        return Mono.just(service.delete(id));
    }

}
