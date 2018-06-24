package cn.thyonline.service;

import cn.thyonline.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @Description:支付
 * @Author: Created by thy
 * @Date: 2018/6/23 1:58
 */
public interface PayService {

    /**
     * 创建支付
     * @param orderDTO
     */
    PayResponse create(OrderDTO orderDTO);

    /**
     * 支付异步通知
     * @param notifyData
     */
    PayResponse notify(String notifyData);

    /**
     * 退款
     * @param orderDTO
     * @return
     */
    RefundResponse refund(OrderDTO orderDTO);
}
