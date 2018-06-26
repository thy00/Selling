package cn.thyonline.dao;

import cn.thyonline.dataobject.SellerInfo;
import cn.thyonline.utils.KeyUtil;
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
 * @Date: 2018/6/25 18:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository infoRepository;

    @Test
    public void save(){
        SellerInfo info = new SellerInfo();
        info.setId(KeyUtil.genUnigueKey());
        info.setUsername("admin");
        info.setPassword("admin");
        info.setOpenid("123");
        SellerInfo sellerInfo = infoRepository.save(info);
        Assert.assertTrue("创建成功",sellerInfo!=null);

    }
    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = infoRepository.findByOpenid("123");
        Assert.assertTrue("创建成功",sellerInfo!=null);
    }
}