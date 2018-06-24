package cn.thyonline.service.impl;

import cn.thyonline.converter.OrderMaster2OrderDTOConverter;
import cn.thyonline.dao.OrderDetailRepository;
import cn.thyonline.dao.OrderMasterRepository;
import cn.thyonline.dataobject.OrderDetail;
import cn.thyonline.dataobject.OrderMaster;
import cn.thyonline.dataobject.ProductInfo;
import cn.thyonline.dto.CartDTO;
import cn.thyonline.dto.OrderDTO;
import cn.thyonline.enums.OrderStatusEnum;
import cn.thyonline.enums.PayStatusEnum;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.exception.SellException;
import cn.thyonline.service.OrderService;
import cn.thyonline.service.PayService;
import cn.thyonline.service.ProductInfoService;
import cn.thyonline.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:订单实现类
 * @Author: Created by thy
 * @Date: 2018/6/21 0:51
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService infoService;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private OrderMasterRepository masterRepository;

    @Autowired
    private PayService payService;

    @Override
    @Transactional//添加事务
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId=KeyUtil.genUnigueKey();

        //1.从数据库查询商品（数量和价格）
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);//定义订单总价，并设置初始值为0
        for (OrderDetail detail:orderDTO.getOrderDetails()){
            ProductInfo info = infoService.findOne(detail.getProductId());
            if (info==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2、计算价格
            orderAmount=info.getProductPrice()
                    .multiply(new BigDecimal(detail.getProductQuantity()))
                    .add(orderAmount);//总价相加
            //订单商品详情入库
            BeanUtils.copyProperties(info,detail);
            detail.setDetailId(KeyUtil.genUnigueKey());
            detail.setOrderId(orderId);
            detailRepository.save(detail);
        }
        //3、将订单信息写入ordermaster数据库
        OrderMaster master= new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,master);
        master.setOrderAmount(orderAmount);
        master.setOrderStatus(OrderStatusEnum.NEW.getCode());
        master.setPayStatus(PayStatusEnum.WAIT.getCode());
        masterRepository.save(master);
        //4、减少库存
//        List<CartDTO> cartDTOS=new ArrayList<>();
        List<CartDTO> cartDTOS = orderDTO.getOrderDetails().stream().map(
                e -> new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        infoService.decreaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO findOne(String orderId) {
        OrderMaster master = masterRepository.findOne(orderId);
        if (master==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> details = detailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(details)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(master,orderDTO);
        orderDTO.setOrderDetails(details);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> masters = masterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> dtos = OrderMaster2OrderDTOConverter.convert(masters.getContent());
        Page<OrderDTO> orderDTOS=new PageImpl<OrderDTO>(dtos,pageable,masters.getTotalElements());
        return orderDTOS;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster master=new OrderMaster();

        //1、判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态修改，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2、修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCELA.getCode());
        BeanUtils.copyProperties(orderDTO,master);
        OrderMaster updateResult = masterRepository.save(master);
        if (updateResult==null){
            log.error("【取消订单】更新失败，orderMaster={}",master);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3、修改库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.error("【取消订单】订单中无商品信息，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOS= orderDTO.getOrderDetails().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        infoService.increaseStock(cartDTOS);
        //4、已支付情况下退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESSC.getCode())){
            //TODO，已修改
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //1、判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2、修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        if (updateResult==null){
            log.error("【完结订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //1、判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //1、判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付】订单支付状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //2、修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESSC.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        if (updateResult==null){
            log.error("【订单支付】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> masters = masterRepository.findAll(pageable);
        List<OrderDTO> dtos = OrderMaster2OrderDTOConverter.convert(masters.getContent());
        Page<OrderDTO> orderDTOS=new PageImpl<OrderDTO>(dtos,pageable,masters.getTotalElements());
        return orderDTOS;
    }
}
