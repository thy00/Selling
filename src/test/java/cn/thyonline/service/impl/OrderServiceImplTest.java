package cn.thyonline.service.impl;

import cn.thyonline.dataobject.OrderDetail;
import cn.thyonline.dto.OrderDTO;
import cn.thyonline.enums.OrderStatusEnum;
import cn.thyonline.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description:订单测试
 * @Author: Created by thy
 * @Date: 2018/6/21 15:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID="123";
    private final String ORDER_ID="12323";

    @Test
    public void create() {
        //添加用户信息
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("女孩");
        orderDTO.setBuyerAddress("武汉");
        orderDTO.setBuyerPhone("12323242342");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //添加购物车信息
        List<OrderDetail> orderDetails=new ArrayList<>();
        OrderDetail detail=new OrderDetail();
        detail.setProductId("1234");
        detail.setProductQuantity(2);
        orderDetails.add(detail);
        OrderDetail detail2=new OrderDetail();
        detail2.setProductId("12345");
        detail2.setProductQuantity(5);
        orderDetails.add(detail2);

        orderDTO.setOrderDetails(orderDetails);
        OrderDTO dto = orderService.create(orderDTO);
        log.info("订单信息》》》 dto={}",dto);
        Assert.assertNotNull(dto);
    }
    @Test
    public void findOne() {
        OrderDTO dto = orderService.findOne(ORDER_ID);
        log.info("单个订单信息》》》 dto={}",dto);
        Assert.assertNotNull(dto);
    }
    @Test
    public void findList() {
        PageRequest reques=new PageRequest(0,2);
        Page<OrderDTO> dtos = orderService.findList(BUYER_OPENID, reques);
        Assert.assertNotEquals(0,dtos.getTotalElements());
    }
    @Test
    public void cancel() {
        OrderDTO dto = orderService.findOne(ORDER_ID);
        OrderDTO orderDTO = orderService.cancel(dto);
        Assert.assertEquals(OrderStatusEnum.CANCELA.getCode(),orderDTO.getOrderStatus());
    }
    @Test
    public void finish() {
        OrderDTO dto = orderService.findOne(ORDER_ID);
        OrderDTO orderDTO = orderService.finish(dto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),orderDTO.getOrderStatus());
    }
    @Test
    public void paid() {
        OrderDTO dto = orderService.findOne(ORDER_ID);
        OrderDTO orderDTO = orderService.paid(dto);
        Assert.assertEquals(PayStatusEnum.SUCCESSC.getCode(),orderDTO.getPayStatus());
    }

}