package cn.thyonline.dto;

import cn.thyonline.dataobject.OrderDetail;
import cn.thyonline.enums.OrderStatusEnum;
import cn.thyonline.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/20 22:33
 */
@Data
public class OrderDTO {
    private String orderId;//订单号
    private String buyerName;//买家名字
    private String buyerPhone;//买家电话
    private String buyerAddress;//买家地址
    private String buyerOpenid;//买家微信openid
    private BigDecimal orderAmount;//金额
    private Integer orderStatus;//订单状态
    private Integer payStatus;//订单支付状态
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetails;
}
