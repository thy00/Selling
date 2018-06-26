package cn.thyonline.controller;

import cn.thyonline.config.ProjectUrlConfig;
import cn.thyonline.constant.CookieConstant;
import cn.thyonline.constant.RedisConstant;
import cn.thyonline.dataobject.SellerInfo;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.service.SellerService;
import cn.thyonline.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:卖家登录
 * @Author: Created by thy
 * @Date: 2018/6/25 22:36
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private ProjectUrlConfig urlConfig;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 卖家登录
     * @param openid
     * @param map
     * @param response
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam String openid, Map<String,Object> map, HttpServletResponse response){
        //1、匹配数据库
        SellerInfo sellerInfo = sellerService.findByOpenid(openid);
        if (sellerInfo==null){
            map.put("msg",ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/seller/order/index");
            return new ModelAndView("common/error", map);
        }
        //2、设置token至redis
        String token=UUID.randomUUID().toString();
        Integer expire=RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire,TimeUnit.SECONDS);
        //3、设置token至cookie
        CookieUtil.setCookie(response,CookieConstant.TOKEN,token,expire);
        return new ModelAndView("redirect:"+urlConfig.getSellUrl()+"/sell/seller/order/list");//绝对地址
    }

    /**
     * 卖家登出
     * @param map
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(Map<String,Object> map,HttpServletRequest request,HttpServletResponse response){
        //1、从cookie里查询
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        //2、清除redis和cookie
        if(cookie!=null){
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            CookieUtil.setCookie(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGIN_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/index");
        return new ModelAndView("common/success",map);
    }

}
