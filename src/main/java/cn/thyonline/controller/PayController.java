package cn.thyonline.controller;

import cn.thyonline.dto.OrderDTO;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.exception.SellException;
import cn.thyonline.service.OrderService;
import cn.thyonline.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description:支付订单
 * @Author: Created by thy
 * @Date: 2018/6/23 1:49
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    /**
     * 创建微信支付订单
     * @param orderId
     * @param returnUrl
     * @param map
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam String orderId, @RequestParam String returnUrl, Map<String,Object> map){
        //1、查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            log.error("【创建支付】订单不存在");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create");
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        PayResponse payResponse = payService.notify(notifyData);
        //返回微信处理结果
        return new ModelAndView("pay/success");
    }
}
