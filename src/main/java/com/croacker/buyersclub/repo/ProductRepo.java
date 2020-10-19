package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
