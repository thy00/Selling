package cn.thyonline.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description:json格式化工具
 * @Author: Created by thy
 * @Date: 2018/6/23 17:03
 */
public class JsonUtil {

    /**
     * 将对象转换成json
     * @param object
     * @return
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
