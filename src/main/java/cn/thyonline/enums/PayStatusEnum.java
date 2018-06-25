package cn.thyonline.enums;

import lombok.Getter;

/**
 * @Description:订单支付状态
 * @Author: Created by thy
 * @Date: 2018/6/20 18:44
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESSC(1,"支付成功")
    ;
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
