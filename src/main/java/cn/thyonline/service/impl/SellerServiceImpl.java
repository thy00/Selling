package cn.thyonline.service.impl;

import cn.thyonline.dao.SellerInfoRepository;
import cn.thyonline.dataobject.SellerInfo;
import cn.thyonline.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/25 19:00
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository infoRepository;


    @Override
    public SellerInfo findByOpenid(String openid) {
        return infoRepository.findByOpenid(openid);
    }
}
