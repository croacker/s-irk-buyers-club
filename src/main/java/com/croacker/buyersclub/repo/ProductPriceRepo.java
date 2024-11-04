package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductPriceRepo extends ReactiveCrudRepository<ProductPrice, Long> {

    Flux<ProductPrice> findByDeletedIsFalse(Pageable pageable);

    Flux<ProductPrice> findByProduct(Product product);

    Mono<ProductPrice> findByProductAndShopAndPriceDate(Product product,
                                                        Shop shop,
                                                        LocalDateTime priceDate);
}
