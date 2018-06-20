package cn.thyonline.service.impl;

import cn.thyonline.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl categoryService;

    @Test
    public void findOneByCategoryId() {
        ProductCategory category = categoryService.findOneByCategoryId(1);
        Assert.assertEquals(new Integer(1),category.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categories = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0,categories.size());
    }

    @Test
    public void save() {
        ProductCategory category1 = new ProductCategory("非常非常好", 6);
        ProductCategory save = categoryService.save(category1);
        Assert.assertNotNull(save);
    }
}