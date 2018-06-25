package cn.thyonline.dto;

import cn.thyonline.dataobject.OrderDetail;
import cn.thyonline.enums.OrderStatusEnum;
import cn.thyonline.enums.PayStatusEnum;
import cn.thyonline.utils.EnumUtil;
import cn.thyonline.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.commons.lang3.EnumUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/20 22:33
 */

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;//订单号
    private String buyerName;//买家名字
    private String buyerPhone;//买家电话
    private String buyerAddress;//买家地址
    private String buyerOpenid;//买家微信openid
    private BigDecimal orderAmount;//金额
    private Integer orderStatus;//订单状态
    private Integer payStatus;//订单支付状态

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetails;//用来数据传输

    //卖家端页面展示
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
