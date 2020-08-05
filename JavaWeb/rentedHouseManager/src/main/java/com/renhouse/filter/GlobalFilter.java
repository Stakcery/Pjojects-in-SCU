package com.renhouse.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GlobalFilter
 * @Description TODO 权限控制
 * @Author 1TreeForest
 * @Date 2020/7/13 12:59
 * @Version 1.0
 * <p>
 * 权限过滤器，用来阻止用户在没有登录状态下访问除登录页、注册页外所有页面
 * 如果没有登陆，跳转到提示页面，提示用户登录
 * 如果用户已经登录，则放行
 */
public class GlobalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Object user = httpServletRequest.getSession().getAttribute("user");
        String path = httpServletRequest.getRequestURI();

        if (path.indexOf("/login.jsp") + path.indexOf("/register.jsp") + path.indexOf("/pleaselogin.jsp") > -1) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (user == null) {
            servletRequest.setAttribute("msg", "请先登录！");
//            servletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse); //请求转发
            httpServletResponse.sendRedirect("../pages/pleaselogin.jsp");  //页面重定向
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
