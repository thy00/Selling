package cn.thyonline.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:微信账户相关配置
 * @Author: Created by thy
 * @Date: 2018/6/22 21:17
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;//公众平台id
    private String mpAppSecret;//公众平台密匙

    private String mchId;//商户号
    private String mchKey;//商户密匙
    private String keyPath;//商户证书路径

    private String openAppId;//开放平台id
    private String openAppSecret;//开放平台密匙

    private String notifyUrl;//微信支付异步通知地址

    private Map<String,String> templateId;//微信消息模板

}
