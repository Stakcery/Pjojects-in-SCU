package com.stackery.web;

import com.stackery.pojo.User;
import com.stackery.service.UserService;
import com.stackery.service.impl.UserServiceImpl;
import com.stackery.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    /**
     * 处理登陆的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用Service处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null){
            // 回显错误信息
            request.setAttribute("msg","用户名或密码错误！");
            request.setAttribute("username", username);
            // 跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user",loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    /**
     * 处理注册的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        User user =  WebUtils.copyParamToBean(request.getParameterMap(), new User());
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)){
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
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
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

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }
}
