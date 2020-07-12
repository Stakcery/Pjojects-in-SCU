<%--
  Created by IntelliJ IDEA.
  User: Yangwenhao
  Date: 2020/7/12
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        response.getWriter().write("我执行了");
//        Object user = request.getSession().getAttribute("user");
//        if (user == null){
//            request.getRequestDispatcher("../login.jsp").forward(request, response);
//            return;
//        }
    %>
        我是jsp
</body>
</html>
