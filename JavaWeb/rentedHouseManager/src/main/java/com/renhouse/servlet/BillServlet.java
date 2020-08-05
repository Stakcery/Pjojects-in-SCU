package com.renhouse.servlet;

import com.google.gson.Gson;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Page;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.TenantMaintenanceFee;
import com.renhouse.service.BillService;
import com.renhouse.service.HouseService;
import com.renhouse.service.impl.BillServiceImpl;
import com.renhouse.service.impl.HouseServiceImpl;
import com.renhouse.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class BillServlet extends BaseServlet {
    private BillService billService = new BillServiceImpl();
    private HouseService houseService = new HouseServiceImpl();

    protected void getBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("page"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("limit"), Page.PAGE_SIZE);
        String landlord = (String) request.getSession().getAttribute("landlordName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Bill> billRes = billService.calculateBill(landlord, startDate, endDate);

        try {
            Page<Bill> page = billService.pageForBill(landlord, pageNo, pageSize, billRes);
            List<Bill> items = page.getItems();
            Gson gson = new Gson();
            String toJson = gson.toJson(items);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": \"\"," +
                    "  \"count\": " + page.getPageTotalCount() + "," +
                    "  \"data\": " + toJson +
                    "} ";
            response.getWriter().write(result);
        } catch (Exception e) {
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"尚未输入查询范围或查询范围内没有记录\"" +
                    "} ";
            response.getWriter().write(result);
        }
    }
}
