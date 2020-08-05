package com.renhouse.service.impl;

import com.renhouse.dao.HouseDao;
import com.renhouse.dao.impl.HouseDaoImpl;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Page;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.HouseStatus;
import com.renhouse.pojo.vo.NearDateHouse;
import com.renhouse.pojo.vo.TenantMaintenanceFee;
import com.renhouse.service.HouseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseServiceImpl implements HouseService {
    private HouseDao houseDao = new HouseDaoImpl();
    @Override
    public void addHouse(House house) {
        houseDao.addHouse(house);
    }

    @Override
    public void deleteHouseById(Integer id) {
        houseDao.deleteHouseById(id);
    }

    @Override
    public void updateHouse(House house) {
        houseDao.updateHouse(house);
    }

    @Override
    public House queryHouseById(Integer id) {
        HouseDao houseDao = new HouseDaoImpl();
        return houseDao.queryHouseById(id);
    }

    @Override
    public List<House> queryHouses() {
        return houseDao.queryHouses();
    }

    @Override
    public Page<House> page(String username, int pageNo, int pageSize) {
        Page<House> page = new Page<House>();
        HouseDao houseDao = new HouseDaoImpl();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = houseDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<House> items = houseDao.queryForPageItemsByLandlord(begin, pageSize, username);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public List<HouseStatus> queryHouseByLandlordAndStatus_Already(String landlord) {
        return  houseDao.queryHouseByLandlordAndStatus_Already(landlord);
    }

    @Override
    public Page<HouseStatus> pageForRentedHouse(String username, int pageNo, int pageSize) {
        Page<HouseStatus> page = new Page<HouseStatus>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = houseDao.queryHouseByLandlordAndStatusAlreadyCount(username);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<HouseStatus> items = houseDao.queryForPageItemsByRentedStatus(begin, pageSize,username);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<HouseStatus> pageForRentedHouse(String username, String realName, int pageNo, int pageSize) {
        Page<HouseStatus> page = new Page<HouseStatus>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = houseDao.queryHouseByLandlordAndStatusAlreadyCount(username,realName);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<HouseStatus> items = houseDao.queryForPageItemsByRentedStatus(begin, pageSize,username, realName);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<House> pageForUnRentedHouse(String username, int pageNo, int pageSize) {
        Page<House> page = new Page<House>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = houseDao.queryForUnRentedHouseCount(username);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<House> items = houseDao.queryForUnRentedHouseItems(begin, pageSize,username);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<TenantMaintenanceFee> pageForMaintenanceFee(String username, String tenant,int pageNo, int pageSize) {
        Page<TenantMaintenanceFee> page = new Page<TenantMaintenanceFee>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = houseDao.queryMaintenanceFeeByLandlordCount(username,tenant);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<TenantMaintenanceFee> items = houseDao.queryPagesForMaintenanceFeeByLandlordAndTenant(begin, pageSize,username,tenant);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<TenantMaintenanceFee> pageForMaintenanceFee(String username, int pageNo, int pageSize) {
        Page<TenantMaintenanceFee> page = new Page<TenantMaintenanceFee>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = houseDao.queryMaintenanceFeeByLandlordCount(username);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<TenantMaintenanceFee> items = houseDao.queryPagesForMaintenanceFeeByLandlordAndTenant(begin, pageSize,username);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<NearDateHouse> pageForNearDate(String username, int pageNo, int pageSize) {
        Page<NearDateHouse> page = new Page<NearDateHouse>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = houseDao.queryForNearDateCount(username);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<NearDateHouse> items = houseDao.queryForNearDateItems(begin, pageSize,username);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Map<String, Object> queryMonthRentedHouseByLandlord(String username, String year) {
        ArrayList arrayList = new ArrayList();
        Map<String,Object> map = new HashMap<>();
        String[] month = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        for (int i = 0; i < month.length; i++) {
            Integer integer = houseDao.queryMonthRentedHouseByLandlord(username, year, month[i]);
            if (month[i].length()==1){
                String temp = "0"+month[i];
                map.put(temp,integer);
            }else {
                map.put(month[i],integer);
            }
        }
        return map;
    }
}
