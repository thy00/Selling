package cn.thyonline.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:微信认证
 * @Author: Created by thy
 * @Date: 2018/6/22 16:49
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法了。。。");
        log.info("code={}",code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa8bb2e54713cb7be&secret=0ab67e96f71536f279464f6f843e977c&code="+code+"&grant_type=authorization_code";
        RestTemplate template=new RestTemplate();
        String response = template.getForObject(url, String.class);
        log.info("respanse={}",response);
    }
}
