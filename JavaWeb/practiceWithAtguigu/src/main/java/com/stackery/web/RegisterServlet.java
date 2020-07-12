package com.stackery.web;

import com.stackery.pojo.User;
import com.stackery.service.UserService;
import com.stackery.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        //检查验证码是否正确
        if ("abcde".equalsIgnoreCase(code)){
            //检查用户名是否正确
            if (userService.existsUsername(username) == true){
                // 回显错误
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                // 跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else {
                //保存到数据库
                userService.registUser(new User(null, username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }else {
            //把回显信息保存到request域中
            request.setAttribute("msg","验证码输入错误！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            // 跳回注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
