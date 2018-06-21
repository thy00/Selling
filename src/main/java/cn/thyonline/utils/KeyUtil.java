package cn.thyonline.utils;

import java.util.Random;

/**
 * @Description:生成随机数
 * @Author: Created by thy
 * @Date: 2018/6/21 14:03
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUnigueKey(){//同步多线程
        Random random=new Random();
        Integer num = random.nextInt(900000) + 100000;
        return String.valueOf(System.currentTimeMillis()+num);
    }
}
