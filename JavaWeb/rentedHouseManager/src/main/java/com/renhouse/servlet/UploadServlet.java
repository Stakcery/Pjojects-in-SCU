package com.renhouse.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class UploadServlet extends HttpServlet {

    /**
     * 文件上传
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //判断是否是多段数据
        if(ServletFileUpload.isMultipartContent(req)){
            //创建工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建解析数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析数据得到每一格表单项FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //循环判断每一项是普通类还是上传的文件
                for (FileItem fileItem : list) {

                    if(!(fileItem.isFormField())){
                        //保存上传的文件
                        String path = req.getServletContext().getRealPath("static\\images\\userImages");
                        fileItem.write(new File(path+ "\\" + fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
