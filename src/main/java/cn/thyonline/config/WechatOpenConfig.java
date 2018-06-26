package cn.thyonline.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description:微信开放平台配置
 * @Author: Created by thy
 * @Date: 2018/6/25 20:43
 */
@Component
public class WechatOpenConfig {

    @Autowired
    private WechatAccountConfig accountConfig;
    @Bean
    public WxMpService wxOpenService(){
        WxMpServiceImpl wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage memoryConfigStorage = new WxMpInMemoryConfigStorage();//基于内存
        memoryConfigStorage.setAppId(accountConfig.getOpenAppId());
        memoryConfigStorage.setSecret(accountConfig.getOpenAppSecret());
        return memoryConfigStorage;
    }
}
