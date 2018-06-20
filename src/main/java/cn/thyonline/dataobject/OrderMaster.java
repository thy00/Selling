package cn.thyonline.dataobject;

import cn.thyonline.enums.OrderStatusEnum;
import cn.thyonline.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:订单信息
 * @Author: Created by thy
 * @Date: 2018/6/20 18:32
 */
@Entity
@Data
@DynamicUpdate//更新时间
public class OrderMaster {

    @Id
    private String orderId;//订单号
    private String buyerName;//买家名字
    private String buyerPhone;//买家电话
    private String buyerAddress;//买家地址
    private String buyerOpenid;//买家微信openid
    private BigDecimal orderAmount;//金额
    private Integer orderStatus=OrderStatusEnum.NEW.getCode();//订单状态，默认状态为新下单0
    private Integer payStatus=PayStatusEnum.WAIT.getCode();//订单支付状态，默认未支付0
    private Date createTime;
    private Date updateTime;
//    @Transient
//    private List<OrderDetail> orderDetails;
}
