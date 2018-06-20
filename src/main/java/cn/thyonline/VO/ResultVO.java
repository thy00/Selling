package cn.thyonline.VO;

import lombok.Data;

/**
 * @Description:http请求返回给前端的对象
 * @Author: Created by thy
 * @Date: 2018/6/20 13:27
 */
@Data
public class ResultVO<T> {
    private Integer code;//错误码
    private String msg;//提示信息
    private T data;//返回内容
}
