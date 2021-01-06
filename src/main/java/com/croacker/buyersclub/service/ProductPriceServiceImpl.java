package com.croacker.buyersclub.service;

import com.croacker.buyersclub.repo.ProductPriceRepo;
import com.croacker.buyersclub.repo.ProductRepo;
import com.croacker.buyersclub.repo.ShopRepo;
import com.croacker.buyersclub.service.dto.product.ProductDto;
import com.croacker.buyersclub.service.dto.productprice.AddProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceDto;
import com.croacker.buyersclub.service.dto.productprice.ProductPriceInfoDto;
import com.croacker.buyersclub.service.dto.shop.ShopDto;
import com.croacker.buyersclub.service.dto.telegram.TelegramProductPriceDto;
import com.croacker.buyersclub.service.mapper.productprice.AddDtoToProductPrice;
import com.croacker.buyersclub.service.mapper.productprice.DtoToProductPrice;
import com.croacker.buyersclub.service.mapper.productprice.ProductPriceToDto;
import com.croacker.buyersclub.service.mapper.productprice.ProductPriceToInfoDto;
import com.croacker.buyersclub.service.mapper.telegram.ProductPriceToTelegramDto;
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

    private final ProductPriceToInfoDto toInfoDtoMapper;

    private final DtoToProductPrice toEntityMapper;

    private final AddDtoToProductPrice addToEntityMapper;

    private final ProductPriceToTelegramDto toTelegramDtoMapper;

    @Override
    public List<ProductPriceInfoDto> findAll(Pageable pageable) {
        return StreamSupport.stream(repo.findAll().spliterator(), false).map(toInfoDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ProductPriceInfoDto findOne(Long id) {
        return repo.findById(id).map(toInfoDtoMapper).orElse(null);
    }

    @Override
    public List<ProductPriceInfoDto> findByProduct(Long id) {
        var product = productRepo.findById(id).get();
        return StreamSupport.stream(repo.findByProduct(product).spliterator(), false)
                .map(toInfoDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ProductPriceDto findPrice(ProductDto productDto, ShopDto shopDto, LocalDateTime priceDate) {
        var product = productRepo.findById(productDto.getId()).get();
        var shop = shopRepo.findById(shopDto.getId()).get();
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

    @Override
    public List<TelegramProductPriceDto> getProductsPrices(String expression) {
        return productRepo.findByNameContainingIgnoreCase(expression)
                .stream().map(product -> repo.findByProduct(product))
                .flatMap(List::stream)
                .map(toTelegramDtoMapper).collect(Collectors.toList());
    }
}
