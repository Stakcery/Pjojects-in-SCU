package com.stackery.web;

import com.stackery.pojo.Book;
import com.stackery.service.BookService;
import com.stackery.service.impl.BookServiceImpl;
import com.stackery.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.addBook(book);
        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"));
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        //        1，获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //        2，调用BookService . updateBook( book ); 修改图书
        bookService.updateBook(book);
        //        3，重定向回图书列表管理页面地址: /工程名/manager/bookServlet?action=list
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");


    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        //1获取请求的参数图书编号
        int id = WebUtils.parseInt(request.getParameter("id"));
        //2调用bookService.queryBookById查询图书
        Book book =  bookService.queryBookById(id);
        //3保存到图书到Request域中
        request.setAttribute("book", book);
        //4请求转发到pages/manager/book_ edit. jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=utf-8");
        //通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        System.out.println(books);
        //全部图书信息保存到Request域中
        request.setAttribute("books", books);
        //请求转发
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

}
