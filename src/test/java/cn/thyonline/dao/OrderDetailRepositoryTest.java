package cn.thyonline.dao;

import cn.thyonline.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/20 20:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;
    @Test
    public void saveTest(){
        OrderDetail detail=new OrderDetail();
        detail.setDetailId("1232");
        detail.setOrderId("12323");
        detail.setProductIcon(String.valueOf(43));
        detail.setProductId("2342");
        detail.setProductName("咖啡");
        detail.setProductPrice(BigDecimal.valueOf(8989));
        detail.setProductQuantity(2);
        OrderDetail save = repository.save(detail);
        Assert.assertNotNull(save);
    }
    @Test
    public void findByOrOrderId() {
        PageRequest request=new PageRequest(0,2);
        List<OrderDetail> details = repository.findByOrderId("12323");
        Assert.assertNotEquals(0,details.size());
    }
}