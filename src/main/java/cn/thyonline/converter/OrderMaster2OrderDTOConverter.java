package cn.thyonline.converter;

import cn.thyonline.dataobject.OrderMaster;
import cn.thyonline.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:转换器
 * @Author: Created by thy
 * @Date: 2018/6/21 18:03
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasters){
        return orderMasters.stream()
                .map(e->convert(e))
                .collect(Collectors.toList());
    }

}
