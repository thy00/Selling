package cn.thyonline.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:项目中用到的url
 * @Author: Created by thy
 * @Date: 2018/6/25 22:06
 */
@Data
@ConfigurationProperties(prefix ="projectUrl" )
@Component
public class ProjectUrlConfig {

    public String wechatMpAuthorize;//微信公众号授权url
    public String wechatOpenAuthorize;//微信开放平台授权url
    public String sellUrl;//项目url
}
