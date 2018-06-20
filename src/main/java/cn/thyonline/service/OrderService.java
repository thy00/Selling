package cn.thyonline.service;

import cn.thyonline.dataobject.OrderMaster;
import cn.thyonline.dto.OrderDTO;

/**
 * @Description:订单
 * @Author: Created by thy
 * @Date: 2018/6/20 20:38
 */
public interface OrderService {
    /**
     * 查询订单
     * @param orderDTO 传输用的实体
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
