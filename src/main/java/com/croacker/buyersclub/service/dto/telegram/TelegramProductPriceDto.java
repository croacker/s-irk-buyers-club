package com.croacker.buyersclub.service.dto.telegram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TelegramProductPriceDto {

    private Long priceId;

    private Long productId;

    private String productName;

    private Long shopId;

    private String shopName;

    private String price;

    private String priceDate;
}
