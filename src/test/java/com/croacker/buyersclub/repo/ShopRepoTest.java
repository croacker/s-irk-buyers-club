package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopRepoTest {

    @Autowired
    ShopRepo shopRepo;

    @Test
    public void shouldInsertShop(){
        var shop = new Shop().setName("test-shop");
        Shop s = shopRepo.save(shop).block();

        Shop s1 = shopRepo.findById(s.getId()).block();
        assertEquals(s, s1);
    }

}