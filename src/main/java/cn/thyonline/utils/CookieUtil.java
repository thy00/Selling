package cn.thyonline.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:cookie工具
 * @Author: Created by thy
 * @Date: 2018/6/25 23:50
 */
public class CookieUtil {
    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge 过期时间
     */
    public static void setCookie(HttpServletResponse response,
                                 String name,
                                 String value,
                                 int maxAge){

        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获得cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request,String name){

        Map<String,Cookie> map=readCookieMap(request);
        if (map.containsKey(name)){
            return map.get(name);
        }
        return null;
    }

    /**
     * cookie封装成map
     * @param request
     * @return
     */
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> map=new HashMap<>();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                map.put(cookie.getName(),cookie);
            }
        }
        return map;
    }
}
