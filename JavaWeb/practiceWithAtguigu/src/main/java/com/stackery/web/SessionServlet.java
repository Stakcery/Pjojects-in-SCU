package com.stackery.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet {
    protected void createSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean aNew = session.isNew();
        String id = session.getId();
        response.getWriter().write("得到这个SESSION，ID="+id+"<br/>");
        response.getWriter().write("这个ID是否为新建的"+aNew+"<br/>");
    }

    protected void setAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("key1","value1");
    }

    protected void getAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object key1 = request.getSession().getAttribute("key1");
        response.getWriter().write("Session中获取的Key1是"+key1);
    }

    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        response.getWriter().write("默认Session超时时间："+maxInactiveInterval+"<br/>");
    }

    protected void life3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3);
        response.getWriter().write("Session超时时间设置为"+3+"s");
    }

    protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
    }
}
