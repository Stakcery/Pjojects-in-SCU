package com.renhouse.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

    /**
     * 查找指定名称的Cookie,如果参数为空返回null。如果查找不到也返回null
     * @param name
     * @param cookies
     * @return
     */
    public static Cookie findCookie(String name, Cookie[] cookies){
        if (name == null || cookies ==null || cookies.length ==0){
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())){
                return cookie;
            }
        }

        return null;
    }
}
