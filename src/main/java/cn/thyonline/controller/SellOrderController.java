package cn.thyonline.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:卖家端订单
 * @Author: Created by thy
 * @Date: 2018/6/24 0:29
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {

    @GetMapping("/list")
    public ModelAndView list(){
        return null;
    }
}
