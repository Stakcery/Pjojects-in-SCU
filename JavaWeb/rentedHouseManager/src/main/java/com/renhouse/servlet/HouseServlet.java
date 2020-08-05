package com.renhouse.servlet;

import com.google.gson.Gson;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Page;
import com.renhouse.pojo.vo.HouseStatus;
import com.renhouse.pojo.vo.NearDateHouse;
import com.renhouse.pojo.vo.TenantMaintenanceFee;
import com.renhouse.service.HouseService;
import com.renhouse.service.impl.HouseServiceImpl;
import com.renhouse.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class HouseServlet extends BaseServlet {
    private HouseService houseService = new HouseServiceImpl();

    protected void addHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        House house = WebUtils.copyParamToBean(request.getParameterMap(), new House());
        house.setId(null);
        house.setLandlord((String) request.getSession().getAttribute("landlordName"));
        house.setMaintenanceFee(null);
        try {
            house.setStartTime(null);
            house.setEndTime(null);
            house.setTenant("暂无");
            house.setMaintenanceFee(BigDecimal.valueOf(0));
            houseService.addHouse(house);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"添加成功！\"" +
                    "} ";
            response.getWriter().write(result);
        } catch (Exception e) {
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"添加失败！\"" +
                    "} ";
            response.getWriter().write(result);
        }
    }

    protected void editHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean status = false;
        House house = null;
        String monthRent = request.getParameter("monthRent");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if (monthRent == null) {
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"租金只能为数字！\"" +
                    "} ";
            response.getWriter().write(result);
        } else {
            try {
                Integer.parseInt(monthRent);
                house = WebUtils.copyParamToBean(request.getParameterMap(), new House());
                if (house.getSpace() == 0) {
                    String result = "{" +
                            "  \"code\": 1," +
                            "  \"msg\": " + "\"面积只能为数字！\"" +
                            "} ";
                    response.getWriter().write(result);
                } else {
                    status = true;
                }
            } catch (Exception e) {
                String result = "{" +
                        "  \"code\": 1," +
                        "  \"msg\": " + "\"租金只能为数字！\"" +
                        "} ";
                response.getWriter().write(result);
            }
        }

        if (status) {
            try {
                House houseInfo = houseService.queryHouseById(house.getId());
                house.setLandlord(houseInfo.getLandlord());
                if (houseInfo.getMaintenanceFee()==null){
                    house.setMaintenanceFee(new BigDecimal(0));
                }else {
                    house.setMaintenanceFee(houseInfo.getMaintenanceFee());
                }

                if ("未租赁".equals(request.getParameter("rentalStatus"))) {
                    house.setTenant("暂无");
                    house.setStartTime(null);
                    house.setEndTime(null);
                    house.setMaintenanceFee(null);
                } else {
                    house.setTenant(request.getParameter("tenant"));
                    house.setStartTime(startTime);
                    house.setEndTime(endTime);
                }
                houseService.updateHouse(house);
                String result = "{" +
                        "  \"code\": 0," +
                        "  \"msg\": " + "\"修改成功！\"" +
                        "} ";
                response.getWriter().write(result);
            } catch (Exception e) {
                String result = "{" +
                        "  \"code\": 1," +
                        "  \"msg\": " + "\"修改失败！\"" +
                        "} ";
                response.getWriter().write(result);
            }
        }
    }

    protected void deleteHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String temp = request.getParameter("id");
            Integer id = Integer.valueOf(temp);
            houseService.deleteHouseById(id);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"删除成功！\"" +
                    "} ";
            response.getWriter().write(result);
        } catch (Exception e) {
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"删除失败！\"" +
                    "} ";
            response.getWriter().write(result);
        }
    }

    /**
     * 处理分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("limit"), Page.PAGE_SIZE);

        Page<House> page = houseService.page((String) req.getSession().getAttribute("landlordName"), pageNo, pageSize);
        List<House> items = page.getItems();
        Gson gson = new Gson();
        String toJson = gson.toJson(items);
        String result = "{" +
                "  \"code\": 0," +
                "  \"msg\": \"\"," +
                "  \"count\": " + page.getPageTotalCount() + "," +
                "  \"data\": " + toJson +
                "} ";
        resp.getWriter().write(result);
    }

    /**
     * 得到已租赁房屋用户信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageForRented(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("limit"), Page.PAGE_SIZE);
        String realName = req.getParameter("realName");
        if (realName == null){
            Page<HouseStatus> page = houseService.pageForRentedHouse((String) req.getSession().getAttribute("landlordName"), pageNo, pageSize);
            List<HouseStatus> items = page.getItems();
            Gson gson = new Gson();
            String toJson = gson.toJson(items);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": \"\"," +
                    "  \"count\": " + page.getPageTotalCount() + "," +
                    "  \"data\": " + toJson +
                    "} ";
            resp.getWriter().write(result);
        }else {
            Page<HouseStatus> page = houseService.pageForRentedHouse((String) req.getSession().getAttribute("landlordName"), realName ,pageNo, pageSize);
            List<HouseStatus> items = page.getItems();
            Gson gson = new Gson();
            String toJson = gson.toJson(items);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": \"\"," +
                    "  \"count\": " + page.getPageTotalCount() + "," +
                    "  \"data\": " + toJson +
                    "} ";
            resp.getWriter().write(result);
        }

    }

    /**
     * 未租赁房屋信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pageForUnRented(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("page"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("limit"), Page.PAGE_SIZE);

        Page<House> page = houseService.pageForUnRentedHouse((String) request.getSession().getAttribute("landlordName"), pageNo, pageSize);
        List<House> items = page.getItems();
        Gson gson = new Gson();
        String toJson = gson.toJson(items);
        String result = "{" +
                "  \"code\": 0," +
                "  \"msg\": \"\"," +
                "  \"count\": " + page.getPageTotalCount() + "," +
                "  \"data\": " + toJson +
                "} ";
        response.getWriter().write(result);
    }

    /**
     * 查询还有十五天到期租赁客户信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pageForNearDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("page"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("limit"), Page.PAGE_SIZE);
        Page<NearDateHouse> page = houseService.pageForNearDate((String) request.getSession().getAttribute("landlordName"), pageNo, pageSize);
        List<NearDateHouse> items = page.getItems();
        Gson gson = new Gson();
        String toJson = gson.toJson(items);
        String result = "{" +
                "  \"code\": 0," +
                "  \"msg\": \"\"," +
                "  \"count\": " + page.getPageTotalCount() + "," +
                "  \"data\": " + toJson +
                "} ";
        response.getWriter().write(result);
    }


    protected void editUnRentedHose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        House house = houseService.queryHouseById(WebUtils.parseInt(id, 0));
        String monthRent = request.getParameter("monthRent");
        String space = request.getParameter("space");
        String address = request.getParameter("address");
        String layout = request.getParameter("layout");
        String houseName = request.getParameter("houseName");
        if (monthRent.length() <= 0 || monthRent == null) {
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"月租金不能为空！\"" +
                    "} ";
            response.getWriter().write(result);
        } else if (space.length() <= 0 || space == null) {
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"面积不能为空！\"" +
                    "} ";
            response.getWriter().write(result);
        } else {
            try {
                house.setMonthRent(new BigDecimal(monthRent));
            } catch (Exception e) {
                String result = "{" +
                        "  \"code\": 1," +
                        "  \"msg\": " + "\"月租金只能为数字！\"" +
                        "} ";
                response.getWriter().write(result);
            }
            try {
                house.setSpace(WebUtils.parseInt(space, 0));
            } catch (Exception e) {
                String result = "{" +
                        "  \"code\": 1," +
                        "  \"msg\": " + "\"面积只能为数字！\"" +
                        "} ";
                response.getWriter().write(result);
            }
            house.setAddress(address);
            house.setLayout(layout);
            house.setHouseName(houseName);
            houseService.updateHouse(house);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"修改成功！\"" +
                    "} ";
            response.getWriter().write(result);
        }

    }

    /**
     * 维护费记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void release(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String endTime = request.getParameter("endTime");
        if (endTime.length() == 0 || endTime == null) {
            String result = "{" +
                    "  \"code\": 1," +
                    "  \"msg\": " + "\"endTime参数不能为空！\"" +
                    "} ";
            response.getWriter().write(result);
        } else {
            String id = request.getParameter("id");
            House house = houseService.queryHouseById(WebUtils.parseInt(id, 0));
            house.setEndTime(endTime);
            houseService.updateHouse(house);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": " + "\"续租成功！\"" +
                    "} ";
            response.getWriter().write(result);
        }
    }

    /**
     * 维护费记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageForFee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("limit"), Page.PAGE_SIZE);

        String tenantName = req.getParameter("tenantName");

        if (tenantName == null || tenantName.length() == 0) {
            Page<TenantMaintenanceFee> page = houseService.pageForMaintenanceFee((String) req.getSession().getAttribute("landlordName"),
                    pageNo, pageSize);
            List<TenantMaintenanceFee> items = page.getItems();
            Gson gson = new Gson();
            String toJson = gson.toJson(items);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": \"\"," +
                    "  \"count\": " + page.getPageTotalCount() + "," +
                    "  \"data\": " + toJson +
                    "} ";
            resp.getWriter().write(result);

        } else {
            Page<TenantMaintenanceFee> page = houseService.pageForMaintenanceFee((String) req.getSession().getAttribute("landlordName"),
                    tenantName, pageNo, pageSize);
            List<TenantMaintenanceFee> items = page.getItems();
            Gson gson = new Gson();
            String toJson = gson.toJson(items);
            String result = "{" +
                    "  \"code\": 0," +
                    "  \"msg\": \"\"," +
                    "  \"count\": " + page.getPageTotalCount() + "," +
                    "  \"data\": " + toJson +
                    "} ";
            resp.getWriter().write(result);
        }


    }

    protected void editFee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        House house = WebUtils.copyParamToBean(request.getParameterMap(), new House());
        House houseInfo = houseService.queryHouseById(house.getId());
        house.setLandlord(houseInfo.getLandlord());
        house.setTenant(houseInfo.getTenant());
        house.setMonthRent(houseInfo.getMonthRent());
        house.setSpace(houseInfo.getSpace());
        house.setRentalStatus(houseInfo.getRentalStatus());
        house.setAddress(houseInfo.getAddress());
        house.setLayout(houseInfo.getLayout());
        house.setStartTime(houseInfo.getStartTime());
        house.setEndTime(houseInfo.getEndTime());
        house.setHouseName(houseInfo.getHouseName());
        houseService.updateHouse(house);
        String result = "{" +
                "  \"code\": 0," +
                "  \"msg\": " + "\"修改成功！\"" +
                "} ";
        response.getWriter().write(result);
    }

}
