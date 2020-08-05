package com.renhouse.junit.service;

import com.renhouse.pojo.House;
import com.renhouse.pojo.Page;
import com.renhouse.pojo.vo.HouseStatus;
import com.renhouse.pojo.vo.NearDateHouse;
import com.renhouse.pojo.vo.TenantMaintenanceFee;
import com.renhouse.service.HouseService;
import com.renhouse.service.impl.HouseServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HouseServiceImplTest {
    HouseService houseService = new HouseServiceImpl();
    @Test
    public void addHouse() {
        House house = new House(null,"admin","xxy",new BigDecimal(500),50,"未租赁","四川省成都市人民南路40号","三室一厅",
                null,null,"夏哥花园",new BigDecimal(100));
        houseService.addHouse(house);
    }

    @Test
    public void deleteHouseById() {
        houseService.deleteHouseById(43);
    }

    @Test
    public void updateHouse() {
    }

    @Test
    public void queryHouseById() {
        System.out.println(houseService.queryHouseById(5));
    }

    @Test
    public void queryHouses() {
    }

    @Test
    public void page() {
    }


    @Test
    public void pageForRentedHouse() {
        Page<HouseStatus> admin = houseService.pageForRentedHouse("admin", 1, 15);
        List<HouseStatus> items = admin.getItems();
        for (HouseStatus houseStatus: items) {
            System.out.println(houseStatus);
        }
    }

    @Test
    public void pageForMaintenanceFee() throws SQLException {
        Page<TenantMaintenanceFee> tenantMaintenanceFeePage = houseService.pageForMaintenanceFee("admin", "aaa", 1, 15);
        List<TenantMaintenanceFee> items = tenantMaintenanceFeePage.getItems();
        for (TenantMaintenanceFee tenantMaintenanceFee: items) {
            System.out.println(tenantMaintenanceFee);
        }
    }

    @Test
    public void pageForUnRentedHouse() {
        Page<House> housePage = houseService.pageForUnRentedHouse("admin", 1, 15);
        List<House> items = housePage.getItems();
        for (House house:items) {
            System.out.println(items);
        }

    }

    @Test
    public void pageForNearDate() {
        Page<NearDateHouse> housePage = houseService.pageForNearDate("admin", 1, 15);
        List<NearDateHouse> items = housePage.getItems();
        for (NearDateHouse house:items) {
            System.out.println(items);
        }
    }

    @Test
    public void testPageForRentedHouse() {
        Page<HouseStatus> admin = houseService.pageForRentedHouse("admin", "杨文豪",1, 15);
        List<HouseStatus> items = admin.getItems();
        for (HouseStatus houseStatus: items) {
            System.out.println(houseStatus);
        }
    }

    @Test
    public void queryMonthRentedHouseByLandlord() {
        Map<String, Object> admin = houseService.queryMonthRentedHouseByLandlord("admin", "2020");
        String[] month = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        Map<String, Object> allMonth = new HashMap<>();
        allMonth.put("01","Jan");
        allMonth.put("02","Feb");
        allMonth.put("03","Mar");
        allMonth.put("04","Apr");
        allMonth.put("05","May");
        allMonth.put("06","Jun");
        allMonth.put("07","Jul");
        allMonth.put("08","Aug");
        allMonth.put("09","Sep");
        allMonth.put("10","Oct");
        allMonth.put("11","Nov");
        allMonth.put("12","Dec");
        for (int i = 0; i < month.length; i++) {
            if (month[i].length()==1){
                String temp = "0"+month[i];
                System.out.println(allMonth.get(temp)+"月："+admin.get(temp));
            }else {
                System.out.println(allMonth.get(month[i])+"月："+admin.get(month[i]));
            }
        }
    }
}