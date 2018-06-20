package cn.thyonline.service.impl;

import cn.thyonline.dao.ProductInfoRepository;
import cn.thyonline.dataobject.ProductInfo;
import cn.thyonline.enums.ProductStatusEnum;
import cn.thyonline.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:商品信息
 * @Author: Created by thy
 * @Date: 2018/6/20 11:25
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository infoRepository;
    @Override
    public ProductInfo findOne(String productId) {
        return infoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return infoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return infoRepository.save(productInfo);
    }
}
