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
    private Long productId;
    private String name;
    private String price;
}
