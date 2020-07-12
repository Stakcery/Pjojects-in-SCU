package com.stackery.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpSession session = ((HttpServletRequest) req).getSession();
        Object user = session.getAttribute("user");
        if (user == null){
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }else {
            //让程序继续往下访问资源
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
