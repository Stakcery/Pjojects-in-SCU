package com.renhouse.service;

import com.renhouse.pojo.Page;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.DetailBill;
import com.renhouse.pojo.vo.TenantMaintenanceFee;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @InterfaceName BillService
 * @Description 账单服务
 * @Author 1TreeForest
 * @Date 2020/7/16 19:14
 * @Version 1.0
 */
public interface BillService {
    /**
     * @param landlord
     * @param startDate yyyy-MM-dd
     * @param endDate   yyyy-MM-dd
     * @return
     */
    public List<Bill> calculateBill(String landlord, String startDate, String endDate);

    /**
     * 辅助排序器
     * @param list
     * @return
     */
    public List<Bill> listSort(List<Bill> list);

    /**
     * 通过房东名查询记录
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Bill> pageForBill(String username, int pageNo, int pageSize, List<Bill> billRes);

    /**
     * TODO 想做详单功能，下拉菜单展开详细信息
     * @param landlord
     * @param startDate
     * @param endDate
     * @return
     */
    List<DetailBill> detailBill(String landlord, String startDate, String endDate);
}
