package cn.thyonline.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:从页面接收的商品信息
 * @Author: Created by thy
 * @Date: 2018/6/25 1:32
 */
@Data
public class ProductForm {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;//库存
    private String productDescription;
    private String productIcon;//小图地址
    private Integer productStatus;//0 正常 1 下架
    private Integer categoryType;//类目编号
}
