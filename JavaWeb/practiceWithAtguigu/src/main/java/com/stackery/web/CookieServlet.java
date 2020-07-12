package com.stackery.web;

import com.stackery.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {

    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        //创建Cookie对象
        Cookie cookie = new Cookie("key1","value1");
        //通知客户端保存cookie
        response.addCookie(cookie);
        response.getWriter().write("Cookie创建成功");

    }

    protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            response.getWriter().write("Cookie:"+cookie.getName()+"="+cookie.getValue()+"<br/>");
        }

    }

    protected void updateCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        Cookie cookie = CookieUtils.findCookie("key1", request.getCookies());
        if (cookie != null){
            cookie.setValue("waowao");
            response.addCookie(cookie);
            response.getWriter().write("Cookie:"+cookie.getName()+"="+cookie.getValue()+"\n");
        }

    }

    protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        Cookie cookie = CookieUtils.findCookie("defaultLife", request.getCookies());
        if (cookie != null){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        Cookie cookie = new Cookie("defaultLife","default");
        cookie.setMaxAge(10);
        response.addCookie(cookie);
    }

    protected void life3600(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        Cookie cookie = new Cookie("life3600","life3600");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
    }

    protected void testPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        Cookie cookie = new Cookie("pathCookie","life3600");
        cookie.setPath(request.getContextPath() + "/pages");
        response.addCookie(cookie);
    }
}
