package cn.thyonline.service.impl;

import cn.thyonline.dataobject.ProductInfo;
import cn.thyonline.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/20 11:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl infoService;
    @Test
    public void findOne() {
        ProductInfo info = infoService.findOne("1234");
        Assert.assertEquals("1234",info.getProductId());
    }
    @Test
    public void findUpAll() {
        List<ProductInfo> all = infoService.findUpAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findAll() {
        PageRequest request=new PageRequest(0,10);
        Page<ProductInfo> infos = infoService.findAll(request);
        Assert.assertNotEquals(0,infos.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo info=new ProductInfo();
        info.setProductId("12345");
        info.setProductName("生菜");
        info.setProductPrice(BigDecimal.valueOf(3));
        info.setProductStock(40);
        info.setProductDescription("味道很好");
        info.setProductIcon("XXXXXXX");
        info.setProductStatus(ProductStatusEnum.DOWN.getCode());
        info.setCategoryType(4);
        ProductInfo save = infoService.save(info);
        Assert.assertNotEquals(null,save);
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = infoService.onSale("12345");
        Assert.assertTrue("商品上架成功",productInfo!=null);
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = infoService.offSale("12345");
        Assert.assertTrue("商品下架成功",productInfo!=null);
    }
}