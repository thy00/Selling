package cn.thyonline.utils;

import cn.thyonline.enums.CodeEnum;

/**
 * @Description:枚举工具类
 * @Author: Created by thy
 * @Date: 2018/6/24 15:44
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
