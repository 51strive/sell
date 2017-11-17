package com.imooc.sell.enums;

import lombok.Getter;

/**
 * Created by liuzhang on 2017/8/3.
 */
@Getter
public enum ProductStatusEunm implements CodeEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEunm(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
