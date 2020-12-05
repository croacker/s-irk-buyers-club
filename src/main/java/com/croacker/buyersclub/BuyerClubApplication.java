package com.croacker.buyersclub;

import com.croacker.buyersclub.telegram.IrkBuyersClubBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuyerClubApplication {

	public static void main(String[] args) {
		new IrkBuyersClubBot().botConnect();
		SpringApplication.run(BuyerClubApplication.class, args);
	}

}
