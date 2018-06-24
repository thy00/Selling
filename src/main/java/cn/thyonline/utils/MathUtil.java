package cn.thyonline.utils;

/**
 * @Description:数字对比
 * @Author: Created by thy
 * @Date: 2018/6/23 22:12
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;
    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public  static Boolean equals(Double d1,Double d2){
        double result = d1 - d2;
        if (result<MONEY_RANGE){
            return true;
        }
        return false;
    }
}
