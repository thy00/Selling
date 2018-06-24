package cn.thyonline.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:微信账户相关配置
 * @Author: Created by thy
 * @Date: 2018/6/22 21:17
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;
    private String mpAppSecret;
    private String mchId;//商户号
    private String mchKey;//商户密匙
    private String keyPath;//商户证书路径

    private String notifyUrl;//微信支付异步通知地址

}
