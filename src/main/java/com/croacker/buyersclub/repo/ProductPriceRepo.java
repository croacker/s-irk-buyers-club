package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductPriceRepo extends CrudRepository<ProductPrice, Long> {

    List<ProductPrice> findByDeletedIsFalse(Pageable pageable);

    List<ProductPrice> findByProduct(Product product);

    Optional<ProductPrice> findByProductAndShopAndPriceDate(Product product,
                                                            Shop shop,
                                                            LocalDateTime priceDate);
}
