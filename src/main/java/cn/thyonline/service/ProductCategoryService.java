package cn.thyonline.service;

import cn.thyonline.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 查询单个类目
     * @param categoryId
     * @return
     */
    ProductCategory findOneByCategoryId(Integer categoryId);

    /**
     * 查询所有
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 通过编号查询种类
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 添加新的种类
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
