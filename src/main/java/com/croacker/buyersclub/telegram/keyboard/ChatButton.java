package com.croacker.buyersclub.telegram.keyboard;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatButton {

    private String text;

    private String data;

}
