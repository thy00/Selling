package cn.thyonline.enums;

import lombok.Getter;

/**
 * @Description:订单状态枚举
 * @Author: Created by thy
 * @Date: 2018/6/20 18:37
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCELA(2,"已取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
