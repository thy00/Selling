package cn.thyonline.exception;

import cn.thyonline.enums.ResultEnum;
import lombok.Getter;

/**
 * @Description:异常处理
 * @Author: Created by thy
 * @Date: 2018/6/21 13:13
 */
@Getter
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code=code;
    }
}
