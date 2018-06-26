package cn.thyonline.dataobject;

import cn.thyonline.enums.ProductStatusEnum;
import cn.thyonline.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 商品信息
 * @Author: Created by thy
 * @Date: 2018/6/20 10:58
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;//库存
    private String productDescription;
    private String productIcon;//小图地址
    private Integer productStatus;//0 正常 1 下架,默认在架
    private Integer categoryType;//类目编号
    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
