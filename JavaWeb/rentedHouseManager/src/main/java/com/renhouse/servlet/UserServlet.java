package com.renhouse.servlet;

import com.google.gson.Gson;
import com.renhouse.pojo.User;
import com.renhouse.service.UserService;
import com.renhouse.service.impl.UserServiceImpl;
import com.renhouse.utils.MD5Utils;
import com.renhouse.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 处理登陆的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("captcha");

        //获取前端生成的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if (token != null && token.equalsIgnoreCase(code)){
            User loginUser = userService.login(new User(null, username, password, null, null));
            if (loginUser != null && code.equalsIgnoreCase(token)){
                // 将user绑定到session域中，便于filter
                request.getSession().setAttribute("user",loginUser);
                request.getSession().setAttribute("landlordName",loginUser.getUsername());
                request.getSession().setAttribute("noticeStatus","true");
                response.sendRedirect("pages/home.jsp");
            }else {
                //密码错误
                //把回显信息保存到request域中
                request.setAttribute("msg","用户名或密码错误，登陆失败！");
                // 跳回登录页面
                request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
            }
        }else {
            //验证码输入错误
            //把回显信息保存到request域中
            request.setAttribute("msg","验证码输入错误！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            // 跳回登录页面
            request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
        }


    }

    /**
     * 处理注册的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  获取请求验证码参数
        String captcha = request.getParameter("captcha");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        // 将前端数据注入user中
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());

        //获取前端生成的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if (token != null && token.equalsIgnoreCase(captcha)){
            //保存到数据库
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            userService.registUser(new User(null, username, password, phone, null));
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("landlordName",user.getUsername());
            request.getSession().setAttribute("noticeStatus","true");
            response.sendRedirect("pages/home.jsp");
        }else {
            //返回前端错误信息
            request.setAttribute("msg","验证码输入错误！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("phone",phone);
            request.getRequestDispatcher("pages/user/register.jsp").forward(request, response);
        }
    }

    /**
     * 处理注销的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除session会话
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    /**
     * 用户名校验,返回是否存在用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void existUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        boolean existUsername = userService.existsUsername(username);

        Map<String, Object> result =  new HashMap<>();
        result.put("existUsername",existUsername);

        Gson gson = new Gson();
        String json = gson.toJson(result);
        response.getWriter().write(json);

    }

    /**
     * 控制弹窗
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void notice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("noticeStatus","false");

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void modifyPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if (password==null || password.length()==0 || repassword==null || repassword.length()==0 ){
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"参数不能为空！\"" +
                    "} ";
            response.getWriter().write(result);
        }else if (password.equals(repassword)){
            userService.modifyPassword((String) request.getSession().getAttribute("landlordName"),password);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"修改成功！\"" +
                    "} ";
            response.getWriter().write(result);
        }else {
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"前后输入密码不一致！\"" +
                    "} ";
            response.getWriter().write(result);
        }
    }
}
