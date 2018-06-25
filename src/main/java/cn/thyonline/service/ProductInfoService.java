package cn.thyonline.service;

import cn.thyonline.dataobject.ProductInfo;
import cn.thyonline.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**商品
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/20 11:20
 */
public interface ProductInfoService {
    /**
     * 查询指定商品
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查看所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查看所有商品（后端）
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOS
     */
    void increaseStock(List<CartDTO> cartDTOS);

    /**
     * 减库存
     * @param cartDTOS
     */
    void decreaseStock(List<CartDTO> cartDTOS);

    /**
     * 上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);
    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);
}
