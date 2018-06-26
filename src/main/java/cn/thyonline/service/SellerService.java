package cn.thyonline.service;

import cn.thyonline.dataobject.SellerInfo;

/**
 * @Description:卖家
 * @Author: Created by thy
 * @Date: 2018/6/25 18:58
 */
public interface SellerService {
    /**
     * 根据openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);
}
