package cn.thyonline.dao;

import cn.thyonline.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @Description:订单详情
 * @Author: Created by thy
 * @Date: 2018/6/20 19:01
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
