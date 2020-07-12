package com.stackery.web;

import com.stackery.pojo.User;
import com.stackery.service.UserService;
import com.stackery.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用Service处理业务
        User login = userService.login(new User(null, username, password, null));
        if (login == null){
            // 回显错误信息
            request.setAttribute("msg","用户名或密码错误！");
            request.setAttribute("username", username);
            // 跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }
}
