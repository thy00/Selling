package cn.thyonline.dto;

import lombok.Data;

/**
 * @Description:购物车
 * @Author: Created by thy
 * @Date: 2018/6/21 15:21
 */
@Data
public class CartDTO {

    private String productId;//商品id
    private Integer productQuantity;//数量

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
