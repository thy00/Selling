package cn.thyonline.dao;

import cn.thyonline.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: ProductInfo的interface
 * @Author: Created by thy
 * @Date: 2018/6/20 11:07
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
