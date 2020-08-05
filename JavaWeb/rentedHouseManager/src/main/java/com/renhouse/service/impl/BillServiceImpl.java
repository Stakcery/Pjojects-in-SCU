package com.renhouse.service.impl;

import com.renhouse.dao.HouseDao;
import com.renhouse.dao.impl.HouseDaoImpl;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Page;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.DetailBill;
import com.renhouse.service.BillService;
import com.renhouse.utils.TimeUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName BillServiceimpl
 * @Description 账单计算服务实现
 * @Author 1TreeForest
 * @Date 2020/7/16 19:19
 * @Version 1.0
 */
public class BillServiceImpl implements BillService {
    private HouseDao houseDao = new HouseDaoImpl();

    @Override
    public List<Bill> calculateBill(String landlord, String startDate, String endDate) {
        List<House> house = houseDao.queryHouseByLandlordAndStatusToCreateBill_Already(landlord);
//        HashMap<String, BigDecimal> res = new HashMap<String, BigDecimal>();
        List<Bill> billRes = new LinkedList<Bill>();

        startDate += "-1";
        endDate += "-28";

        if (TimeUtils.getIntervalDays(startDate, endDate) < 0) { //起始日期大于结束日期则返回错误
            return null;
        }

        int size = house.size();

        for (int i = 0; i < size; i++) {
            int j;
            House houseItem = house.get(i);
            String st = houseItem.getStartTime();
            String et = houseItem.getEndTime();

            if (TimeUtils.getIntervalDays(st, endDate) < 0) { //房屋开租日期晚于查询截止日期则跳过
                continue;
            }

            if (TimeUtils.getIntervalDays(st, startDate) > 0) { //查询的开始日期比租房的开始日期晚
                st = startDate;
            }

            if (TimeUtils.getIntervalDays(et, endDate) < 0) { //查询的终止日期比租房的终止日期早
                et = endDate;
            }

            int intervalDays = (int) TimeUtils.getIntervalDays(st, et) + 1;
            int intervalMonths = intervalDays / 30;

            if (intervalDays % 30 != 0) {
                intervalMonths++;
            }


            String regex = "-";// 按照-来进行切割
            String[] date1 = st.split(regex);
            int year = Integer.parseInt(date1[0]);
            int month = Integer.parseInt(date1[1]);

            while (intervalMonths-- > 0) {
                BigDecimal sum = houseItem.getMonthRent();
                if (houseItem.getMaintenanceFee() != null) {
                    sum = sum.subtract(houseItem.getMaintenanceFee());
                }
                String key = year + "-" + month;

                if (!sum.equals(BigDecimal.ZERO)) { //添加
                    int billResSize = billRes.size();

                    if (billResSize == 0) { //表中没有任何元素
                        Bill billItem = new Bill(key, houseItem.getMonthRent(), BigDecimal.ZERO, sum);
                        if (houseItem.getMaintenanceFee() != null) {
                            billItem.setMaintenanceFee(houseItem.getMaintenanceFee());
                        }
                        billRes.add(billItem);
                        month++;
                        if (month == 13) {
                            month = 1;
                            year++;
                        }
                        continue;
                    }

                    for (j = 0; j < billResSize; j++) { //处理列表中已存在的日期
                        String[] date2 = billRes.get(j).getDate().split(regex);
                        int y2 = Integer.parseInt(date2[0]);
                        int m2 = Integer.parseInt(date2[1]);
                        if (year == y2 && month == m2) {
                            billRes.get(j).setTotal(billRes.get(j).getTotal().add(sum));
                            billRes.get(j).setMonthRent(billRes.get(j).getMonthRent().add(houseItem.getMonthRent()));
                            if (houseItem.getMaintenanceFee() != null) {
                                if (billRes.get(j).getMaintenanceFee() != null) {
                                    billRes.get(j).setMaintenanceFee(billRes.get(j).getMaintenanceFee().add(houseItem.getMaintenanceFee()));
                                } else {
                                    billRes.get(j).setMaintenanceFee(houseItem.getMaintenanceFee());
                                }
                            }
                            break;
                        }
                    }

                    if (j == billResSize) { //表中无此元素
                        Bill billItem = new Bill(key, houseItem.getMonthRent(), BigDecimal.ZERO, sum);
                        if (houseItem.getMaintenanceFee() != null) {
                            billItem.setMaintenanceFee(houseItem.getMaintenanceFee());
                        }
                        billRes.add(billItem);
                    }
                }
                month++;
                if (month == 13) {
                    month = 1;
                    year++;
                }
            }
        }

        //排序
        billRes = this.listSort(billRes);
        return billRes;
    }

    public List<Bill> listSort(List<Bill> list) {
        Collections.sort(list, new Comparator<Bill>() {
            @Override
            public int compare(Bill o1, Bill o2) {
                //升序，o1,o2反过来则降序
                return (int) TimeUtils.getIntervalDays(o2.getDate() + "-1", o1.getDate() + "-1");
            }
        });
        return list;
    }

    @Override
    public List<DetailBill> detailBill(String landlord, String startDate, String endDate) {
        return null;
    }


    //    @Override
//    public List<Bill> detailBill(String landlord, String startDate, String endDate) {
//        List<House> house = houseDao.queryHouseByLandlordAndStatusToCreateBill_Already(landlord);
////        HashMap<String, BigDecimal> calculateRes = new HashMap<String, BigDecimal>();
//        List<Bill> billRse= new  List<Bill>;
//
//        startDate += "-1";
//        endDate += "-1";
//
//        if (startDate.compareTo(endDate) > 0) { //起始日期大于结束日期则返回错误
//            return null;
//        }
//
//        int size = house.size();
//
//        for (int i = 0; i < size; i++) {
//            House item = house.get(i);
//            String st = item.getStartTime();
//            String et = item.getEndTime();
//            if (st.compareTo(startDate) < 0) { //查询的开始日期比租房的开始日期晚
//                st = startDate;
//            }
//            if (et.compareTo(endDate) > 0) { //查询的终止日期比租房的终止日期早
//                et = endDate;
//            }
//
//            int intervalDays = (int) TimeUtils.getIntervalDays(st, et);
//            int intervalMonths = intervalDays / 30;
//
//            if (intervalDays % 30 != 0) {
//                intervalMonths++;
//            }
//
//
//            String regex = "-";// 按照-来进行切割
//            String[] date1 = st.split(regex);
//            int year = Integer.parseInt(date1[0]);
//            int month = Integer.parseInt(date1[1]);
//
//            while (intervalMonths-->0) {
//                BigDecimal sum = item.getMonthRent();
//                if (item.getMaintenanceFee() != null) {
//                    sum.subtract(item.getMaintenanceFee());
//                }
//                System.out.println(sum);
//                String key = year + "-" + month;
//                if(!sum.equals(0)) {
//                    if (res.containsKey(key)) {
//                        res.put(key, res.get(key).add(sum));
//                    } else {
//                        res.put(key, sum);
//                    }
//                }
//                month++;
//                if(month==13) {
//                    month = 1;
//                    year++;
//                }
//            }
//        }
//
//        return res;
//    }

    @Override
    public Page<Bill> pageForBill(String username, int pageNo, int pageSize, List<Bill> billRes) {
        Page<Bill> page = new Page<Bill>();
        LinkedList<Bill> pageItems = new LinkedList<Bill>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = billRes.size();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        for (int i = begin; i < pageTotalCount && i < pageTotalCount; i++) {
            pageItems.add(billRes.get(i));
        }
        // 设置当前页数据
        page.setItems(pageItems);
        return page;
    }
}

