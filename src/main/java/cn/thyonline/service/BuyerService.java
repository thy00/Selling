package cn.thyonline.service;

import cn.thyonline.dto.OrderDTO;

/**
 * @Description:买家
 * @Author: Created by thy
 * @Date: 2018/6/22 2:23
 */
public interface BuyerService {
    /**
     * 查询订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid,String orderId);
}
