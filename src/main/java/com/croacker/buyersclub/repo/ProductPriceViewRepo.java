package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.ProductPriceView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductPriceViewRepo extends ReactiveCrudRepository<ProductPriceView, Long> {

    Mono<Long> countByProductNameContainingIgnoreCase(String expression);

    Flux<ProductPriceView> findByProductNameContainingIgnoreCase(String expression, Pageable pageable);

}
