package cn.thyonline.service.impl;

import cn.thyonline.dataobject.SellerInfo;
import cn.thyonline.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/25 19:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    private static final String openid="123";
    @Autowired
    private SellerService sellerService;

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerService.findByOpenid(openid);
        Assert.assertTrue("查询到卖家信息",sellerInfo!=null);
    }
}