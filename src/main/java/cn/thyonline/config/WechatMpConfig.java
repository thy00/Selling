package cn.thyonline.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description:微信公共号配置
 * @Author: Created by thy
 * @Date: 2018/6/22 21:06
 */
@Component
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig accountConfig;
    @Bean
    public WxMpService wxMpService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage memoryConfigStorage = new WxMpInMemoryConfigStorage();//基于内存
        memoryConfigStorage.setAppId(accountConfig.getMpAppId());
        memoryConfigStorage.setSecret(accountConfig.getMpAppSecret());
        return memoryConfigStorage;
    }

}
