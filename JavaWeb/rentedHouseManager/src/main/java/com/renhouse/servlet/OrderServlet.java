package com.renhouse.servlet;

import com.google.gson.Gson;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Order;
import com.renhouse.pojo.Page;
import com.renhouse.service.HouseService;
import com.renhouse.service.OrderService;
import com.renhouse.service.impl.HouseServiceImpl;
import com.renhouse.service.impl.OrderServiceImpl;
import com.renhouse.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("limit"), Page.PAGE_SIZE);

        Page<Order> page = orderService.page((String) req.getSession().getAttribute("landlordName"),pageNo,pageSize);
        List<Order> items = page.getItems();
        Gson gson = new Gson();
        String toJson = gson.toJson(items);
        String result = "{" +
                "  \"code\": 0," +
                "  \"msg\": \"\"," +
                "  \"count\": "+page.getPageTotalCount()+"," +
                "  \"data\": "+toJson +
                "} ";
        resp.getWriter().write(result);
    }

    protected void editOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = WebUtils.copyParamToBean(request.getParameterMap(),new Order());
        try {
            orderService.updateOrder(order);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"修改成功！\"" +
                    "} ";
            response.getWriter().write(result);
        }catch (Exception e){
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"修改失败！\"" +
                    "} ";
            response.getWriter().write(result);
        }
    }

    protected void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String temp = request.getParameter("id");
            Integer id = Integer.valueOf(temp);
            orderService.deleteOrderById(id);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"删除成功！\"" +
                    "} ";
            response.getWriter().write(result);
        }catch (Exception e){
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"删除失败！\"" +
                    "} ";
            response.getWriter().write(result);
        }
    }


    protected void leaseAgain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String endTime = request.getParameter("endTime");
        HouseService houseService = new HouseServiceImpl();
        System.out.println(request.getSession().getAttribute("user"));
        House queryHouse = houseService.queryHouseById(WebUtils.parseInt(id, 0));
        House house = WebUtils.copyParamToBean(WebUtils.bean2map(queryHouse),new House());
        house.setEndTime(endTime);
        houseService.updateHouse(house);
        String result = "{" +
                "  \"code\": 0," +
                "  \"msg\": " + "\"续约超过！\"" +
                "} ";
        response.getWriter().write(result);
    }
}
