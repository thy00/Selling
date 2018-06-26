package cn.thyonline.service;

import cn.thyonline.dto.OrderDTO;

/**
 * @Description:消息推送
 * @Author: Created by thy
 * @Date: 2018/6/26 12:30
 */
public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);
}
