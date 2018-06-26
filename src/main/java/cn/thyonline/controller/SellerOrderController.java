package cn.thyonline.controller;

import cn.thyonline.dto.OrderDTO;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description:卖家端订单
 * @Author: Created by thy
 * @Date: 2018/6/24 0:29
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOS = orderService.findList(request);
        map.put("orderDTOS",orderDTOS);
        map.put("currentPage",page);
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("cancel")
    public ModelAndView cancel(@RequestParam String orderId,Map<String,Object> map){
        OrderDTO orderDTO = null;
        try {//订单抛出异常处理异常并返回至异常页面
            orderDTO = orderService.findOne(orderId);
            OrderDTO result = orderService.cancel(orderDTO);
        } catch (Exception e) {
                log.error("【卖家端取消订单】发生异常{}",e);
                map.put("msg",e.getMessage());
                map.put("url","/sell/seller/order/list");
                return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam String orderId,Map<String,Object> map){
        OrderDTO orderDTO=null;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (Exception e) {
            log.error("【卖家端完结订单】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam String orderId,Map<String,Object> map){
        OrderDTO orderDTO=null;
        try {
            orderDTO = orderService.findOne(orderId);
            OrderDTO result = orderService.finish(orderDTO);
        } catch (Exception e) {
            log.error("【卖家端查询订单详情】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
