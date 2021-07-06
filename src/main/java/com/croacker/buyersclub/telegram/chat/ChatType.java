package com.croacker.buyersclub.telegram.chat;

public enum ChatType {
    PRODUCT,
    SHOP,
    ORGANIZATION;

    public static ChatType get(String typeName) {
        var type = ChatType.valueOf(typeName);
        if(type == null){
            type = ChatType.PRODUCT;
        }
        return type;
    }
}
