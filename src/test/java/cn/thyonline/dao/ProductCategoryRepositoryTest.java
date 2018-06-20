package cn.thyonline.dao;


import cn.thyonline.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory category = repository.findOne(1);
        System.out.println(category);
    }
    @Test
    public void saveTest(){
        ProductCategory category=new ProductCategory("不好",4);
        repository.save(category);
    }
    @Test
    @Transactional//测试数据会回滚
    public void updateTest(){
        ProductCategory category=new ProductCategory("很不好",5);
        ProductCategory save = repository.save(category);
        Assert.assertNotNull(save);//断言
//        Assert.assertNotEquals(null,save);
    }

    @Test
     public void findByCategoryTypeInTest(){
        List<Integer> list=Arrays.asList(2,3,4);
        List<ProductCategory> categories = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,categories.size());
    }


}