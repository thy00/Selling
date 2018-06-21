package cn.thyonline.controller;

import cn.thyonline.VO.ResultVO;
import cn.thyonline.converter.OrderForm2OrderDTOConverter;
import cn.thyonline.dto.OrderDTO;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.exception.SellException;
import cn.thyonline.form.OrderForm;
import cn.thyonline.service.BuyerService;
import cn.thyonline.service.OrderService;
import cn.thyonline.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:买家订单
 * @Author: Created by thy
 * @Date: 2018/6/21 23:20
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderForm 前端返回的数据
     * @param bindingResult 表单校样结果
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm , BindingResult bindingResult){
        //表单校样是否有错
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //数据转换，并判断购物车是否为空
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        //写入数据库
        OrderDTO result = orderService.create(orderDTO);
        //封装返回前端数据
        Map<String ,String> map=new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 查询订单列表
     * @param openid
     * @param page 设置初始值0
     * @param size 设置初始值10
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam String openid,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size){
        //校样数据
        if (StringUtils.isEmpty(openid)){
            log.error("【订单列表查询】openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //写入数据库
        PageRequest request=new PageRequest(page,size);
        Page<OrderDTO> orderDTOS = orderService.findList(openid, request);
        return ResultVOUtil.success(orderDTOS.getContent());
    }

    /**
     * 查询订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam String openid,
                                     @RequestParam String orderId){
        //校样数据
        if (StringUtils.isEmpty(openid)){
            log.error("【订单列表查询】openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(orderId)){
            log.error("【订单列表查询】订单id不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //TODO 不安全,已修改
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam String openid,
                           @RequestParam String orderId){
        //校样数据
        if (StringUtils.isEmpty(openid)){
            log.error("【订单列表查询】openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(orderId)){
            log.error("【订单列表查询】订单id不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //TODO 不安全,已修改
        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}