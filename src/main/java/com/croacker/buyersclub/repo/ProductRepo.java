package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends ReactiveCrudRepository<Product, Long> {

    Mono<Product> findByName(String name);

    Flux<Product> findByNameContainingIgnoreCase(String expression, Pageable pageable);

    Flux<Product> findByDeletedIsFalse(Pageable pageable);
}
