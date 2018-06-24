package cn.thyonline.service.impl;

import cn.thyonline.dto.OrderDTO;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.exception.SellException;
import cn.thyonline.service.OrderService;
import cn.thyonline.service.PayService;
import cn.thyonline.utils.JsonUtil;
import cn.thyonline.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/23 1:59
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME="微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest request = new PayRequest();
        request.setOpenid(orderDTO.getBuyerOpenid());
        request.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        request.setOrderId(orderDTO.getOrderId());
        request.setOrderName(ORDER_NAME);
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付，request={}",JsonUtil.toJson(request));
        PayResponse response = bestPayService.pay(request);
        log.info("【微信支付】发起支付，response={}",JsonUtil.toJson(response));
        return response;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1、验证签名，sdk已校验
        //2、支付状态，sdk已校验
        //3、支付金额校验
        //4、支付人校验，不用校验
        //获取异步通知
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}",payResponse);
        //修改订单状态
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if (orderDTO==null){//判断订单是否存在
            log.error("【微信支付】异步通知，订单不存在，orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("【微信支付】异步通知，订单金额不一致，orderId={}，微信端金额={}，系统金额={}",orderDTO.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_ERROR);
        }
        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】refundRequest={}",JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】refundResponse={}",JsonUtil.toJson(refundResponse));
        return refundResponse;

    }
}
