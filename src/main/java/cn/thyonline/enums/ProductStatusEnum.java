package cn.thyonline.enums;

import lombok.Getter;

/**
 * @Description:商品状态
 * @Author: Created by thy
 * @Date: 2018/6/20 11:29
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }

}
