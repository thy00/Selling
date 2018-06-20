package cn.thyonline.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Description: 商品信息
 * @Author: Created by thy
 * @Date: 2018/6/20 10:58
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;//库存
    private String productDescription;
    private String productIcon;//小图地址
    private Integer productStatus;//0 正常 1 下架
    private Integer categoryType;//类目编号
}
