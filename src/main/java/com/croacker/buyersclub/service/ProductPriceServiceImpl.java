package com.croacker.buyersclub.service;

import com.croacker.buyersclub.domain.Shop;
import com.croacker.buyersclub.repo.ProductPriceRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.mapper.productprice.AddDtoToProductPrice;
import com.croacker.buyersclub.service.mapper.productprice.DtoToProductPrice;
import com.croacker.buyersclub.service.mapper.productprice.ProductPriceToDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService{

    private final ProductPriceRepo repo;

    private final ProductRepo productRepo;

    private final ShopRepo shopRepo;

    private final ProductPriceToDto toDtoMapper;

    private final DtoToProductPrice toEntityMapper;

    private final AddDtoToProductPrice addToEntityMapper;

    @Override
    public List<ProductPriceDto> findAll(Pageable pageable) {
        return StreamSupport.stream(repo.findAll().spliterator(), false).map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ProductPriceDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null);
    }

    @Override
    public List<ProductPriceDto> findByProduct(String name) {
        var product = productRepo.findByName(name);
        return StreamSupport.stream(repo.findByProduct(product).spliterator(), false)
                .map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ProductPriceDto findPrice(ProductDto product, ShopDto shop, LocalDateTime priceDate) {
        return repo.findByProductAndShopAndPriceDate(product, shop, priceDate).map(toDtoMapper).orElse(null);
    }

    @Override
    public ProductPriceDto save(AddProductPriceDto dto) {
        var shop = shopRepo.findById(dto.getShopId()).get();
        var product = productRepo.findById(dto.getProductId()).get();
        var productPrice = addToEntityMapper.map(dto)
                .setShop(shop)
                .setProduct(product)
                .setPrice(dto.getPrice())
                .setPriceDate(dto.getPriceDate())
                .setDeleted(false);
        productPrice = repo.save(productPrice);
        return toDtoMapper.map(productPrice);
    }

    @Override
    public ProductPriceDto update(ProductPriceDto dto) {
        var cashier = toEntityMapper.map(dto);
        cashier = repo.save(cashier);
        return toDtoMapper.map(cashier);
    }

    @Override
    public ProductPriceDto delete(Long id) {
        return repo.findById(id).map(cashier -> {
            cashier.setDeleted(true);
            cashier = repo.save(cashier);
            return toDtoMapper.map(cashier);
        }).orElse(null);
    }
}
