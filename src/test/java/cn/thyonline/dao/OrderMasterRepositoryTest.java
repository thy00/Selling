package cn.thyonline.dao;

import cn.thyonline.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/20 19:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    private final String OPENID="45353";
    @Autowired
    private OrderMasterRepository masterRepository;

    @Test
    public void saveTest(){
        OrderMaster master=new OrderMaster();
        master.setOrderId("12345");
        master.setBuyerOpenid(String.valueOf(45353));
        master.setBuyerName("师妹");
        master.setBuyerPhone("12332423423242");
        master.setBuyerAddress("武汉");
        master.setOrderId("12323");
        master.setOrderAmount(BigDecimal.valueOf(224));
        OrderMaster save = masterRepository.save(master);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request=new PageRequest(0,2);
        Page<OrderMaster> masters = masterRepository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0,masters.getTotalElements());
    }
}