package cn.thyonline.dao;

import cn.thyonline.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/25 18:45
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
}
