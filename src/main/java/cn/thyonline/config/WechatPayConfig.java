package cn.thyonline.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description:支付配置
 * @Author: Created by thy
 * @Date: 2018/6/23 12:51
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;//注入支付配置

    @Bean
    public BestPayServiceImpl bestPayService(){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config config=new WxPayH5Config();
        config.setAppId(wechatAccountConfig.getMpAppId());
        config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        config.setMchId(wechatAccountConfig.getMchId());
        config.setMchKey(wechatAccountConfig.getMchKey());
        config.setKeyPath(wechatAccountConfig.getKeyPath());
        config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());
        return config;
    }
}
