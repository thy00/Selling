package cn.thyonline.service.impl;

import cn.thyonline.dto.OrderDTO;
import cn.thyonline.service.OrderService;
import cn.thyonline.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/23 13:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1529602085634");
        payService.create(orderDTO);
        //TODO 微信支付测试无法进行，需要服务号商家信息
    }
}