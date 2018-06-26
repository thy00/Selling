package cn.thyonline.aspect;

import cn.thyonline.constant.CookieConstant;
import cn.thyonline.constant.RedisConstant;
import cn.thyonline.exception.SellerAuthorizeException;
import cn.thyonline.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:卖家登录授权
 * @Author: Created by thy
 * @Date: 2018/6/26 1:21
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorize {

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Pointcut("execution(public * cn.thyonline.controller.Seller*.*(..))" +
//            "&&!execution(public * cn.thyonline.controller.SellerUserController.*(..))")
//    public void verify(){
//
//    }
    @Pointcut("execution(public * cn.thyonline.controller.Seller*.*(..))" +
            "&&!execution(public * cn.thyonline.controller.Seller*.*(..))")
    public void verify(){

    }
    @Before("verify()")
    public void doVerify(){
        //获取到HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if (cookie==null){
            log.warn("【登录校样】尚未登录");
            throw new SellerAuthorizeException();
        }
        //查询redis
        String value = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(value)){
            log.warn("【登录校样】redis中查不到，尚未登录");
            throw new SellerAuthorizeException();
        }

    }

}
