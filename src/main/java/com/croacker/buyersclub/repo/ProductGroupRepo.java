package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.ProductGroup;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductGroupRepo extends ReactiveCrudRepository<ProductGroup, Long> {
}
