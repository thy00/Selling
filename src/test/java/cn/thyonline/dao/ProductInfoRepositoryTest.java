package cn.thyonline.dao;

import cn.thyonline.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/20 11:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void saveTest(){
        ProductInfo info=new ProductInfo();
        info.setProductId("1234");
        info.setProductName("生鲜");
        info.setProductPrice(BigDecimal.valueOf(34));
        info.setProductStock(400);
        info.setProductDescription("味道很好");
        info.setProductIcon("XXXXXXX");
        info.setCategoryType(4);
        ProductInfo save = repository.save(info);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfos.size());
    }
    @Test
    public void findOne(){
        ProductInfo one = repository.findOne("1234");
        Assert.assertEquals("1234",one.getProductId());
    }
}