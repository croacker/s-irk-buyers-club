package com.croacker.buyersclub.service.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Общий интерфейс транслятора
 * @param <I> входящий
 * @param <O> исходящий
 */
public interface Mapper<I, O> extends Function<I, O> {

    O map(I input);

    default O apply(I input){
        return map(input);
    }

    default List<O> map(List<I> input){
        return input.stream()
                .map(this::apply)
                .collect(Collectors.toUnmodifiableList());
    }
}
