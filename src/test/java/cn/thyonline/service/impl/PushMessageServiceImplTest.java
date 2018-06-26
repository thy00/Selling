package cn.thyonline.service.impl;

import cn.thyonline.dto.OrderDTO;
import cn.thyonline.service.OrderService;
import cn.thyonline.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/26 13:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageService messageService;
    @Autowired
    private OrderService orderService;

    private static final String orderId="1529572720459";
    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        messageService.orderStatus(orderDTO);
    }
}