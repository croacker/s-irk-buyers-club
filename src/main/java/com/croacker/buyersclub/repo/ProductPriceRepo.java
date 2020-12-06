package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Product;
import com.croacker.buyersclub.domain.ProductPrice;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.Mapper;
import com.croacker.buyersclub.service.mapper.productprice.ProductPriceToDto;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductPriceRepo extends CrudRepository<ProductPrice, Long> {

    List<ProductPrice> findByProduct(Optional<Product> product);

    Optional<ProductPrice> findByProductAndShopAndPriceDate(ProductDto product,
                                                               ShopDto shop,
                                                               LocalDateTime priceDate);
}
