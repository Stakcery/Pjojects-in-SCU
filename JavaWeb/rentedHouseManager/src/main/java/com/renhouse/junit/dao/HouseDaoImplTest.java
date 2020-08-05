package com.renhouse.junit.dao;

import com.renhouse.dao.HouseDao;
import com.renhouse.dao.impl.HouseDaoImpl;
import com.renhouse.pojo.House;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.HouseStatus;
import com.renhouse.pojo.vo.NearDateHouse;
import com.renhouse.pojo.vo.TenantMaintenanceFee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class HouseDaoImplTest {

    @Test
    public void queryHouseByLandlord() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryHouseByLandlord("admin"));
    }

    @Test
    public void queryHouseByLandlordAndTenant() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryHouseByLandlordAndTenant("admin","ywh"));
    }

    @Test
    public void addHouse() {
        HouseDao houseDao = new HouseDaoImpl();
        String landlord = "admin";
        for (int i = 21; i <= 40; i++) {
            String address = "四川省成都市人民南路" + i + "号";
            House house = new House(null,landlord,"xxy",new BigDecimal(500),50,"未租赁",address,"三室一厅",
                    null,null,"夏哥花园",new BigDecimal(50));
            houseDao.addHouse(house);
        }
    }

    @Test
    public void deleteHouseById() {
        HouseDao houseDao = new HouseDaoImpl();
        //返回1说明成功
        System.out.println(houseDao.deleteHouseById(99));
    }

    @Test
    public void updateHouse() {
        HouseDao houseDao = new HouseDaoImpl();
        String landlord = "admin";
        House house = new House(42,landlord,"xxy",new BigDecimal(500),50,"已租赁","四川省成都市人民南路39号"
                ,"三室一厅",
                "2020-7-15","2020-7-17","夏哥花园",new BigDecimal(500));
        houseDao.updateHouse(house);
    }

    @Test
    public void queryHouseById() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryHouseById(5));
    }

    @Test
    public void queryHouses() {
        HouseDao houseDao = new HouseDaoImpl();
        List<House> houses = houseDao.queryHouses();
        for (House house:houses) {
            System.out.println(house);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        HouseDao houseDao = new HouseDaoImpl();
        List<House> houses = houseDao.queryForPageItems(0, 10);
        for (House house: houses) {
            System.out.println(house);
        }
    }

    @Test
    public void queryHouseByLandlordAndStatus_Already() {
        HouseDao houseDao = new HouseDaoImpl();
        List<HouseStatus> admin = houseDao.queryHouseByLandlordAndStatus_Already("admin");
        for (HouseStatus houseStatus:admin) {
            System.out.println(admin);
        }
    }


    @Test
    public void queryHouseByLandlordAndStatusAlreadyCount() {
        HouseDao houseDao = new HouseDaoImpl();
        Integer admin = houseDao.queryHouseByLandlordAndStatusAlreadyCount("admin");
        System.out.println(admin.intValue());
    }

    @Test
    public void queryMaintenanceFeeByLandlordCount() {
        HouseDao houseDao = new HouseDaoImpl();
        Integer integer = houseDao.queryMaintenanceFeeByLandlordCount("admin", "mh");
        System.out.println(integer.intValue());
    }

    @Test
    public void queryPagesForMaintenanceFeeByLandlordAndTenant() {
        HouseDao houseDao = new HouseDaoImpl();
        List<TenantMaintenanceFee> tenantMaintenanceFees = houseDao.queryPagesForMaintenanceFeeByLandlordAndTenant(1, 6, "admin", "mh");
        for (TenantMaintenanceFee tenantMaintenanceFee: tenantMaintenanceFees) {
            System.out.println(tenantMaintenanceFee);
        }
    }

    @Test
    public void queryForUnRentedHouseCount() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryForUnRentedHouseCount("admin"));
    }

    @Test
    public void queryForUnRentedHouseItems() {
        HouseDao houseDao = new HouseDaoImpl();
        List<House> houses = houseDao.queryForUnRentedHouseItems(1, 15, "admin");
        for (House house:houses) {
            System.out.println(house);
        }
    }

    @Test
    public void queryForNearDateCount() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryForNearDateCount("admin"));
    }

    @Test
    public void queryForNearDateItems() {
        HouseDao houseDao = new HouseDaoImpl();
        List<NearDateHouse> a = houseDao.queryForNearDateItems(1, 15, "admin");
        for (NearDateHouse nearDateHouse:a) {
            System.out.println(nearDateHouse);
        }
    }

    @Test
    public void queryHouseByLandlordAndStatusToCreateBill_Already() {
        HouseDao houseDao = new HouseDaoImpl();
        List<House> t1 = houseDao.queryHouseByLandlordAndStatusToCreateBill_Already("admin");
        System.out.println(t1);
    }

    @Test
    public void testQueryHouseByLandlordAndStatusAlreadyCount() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryHouseByLandlordAndStatusAlreadyCount("admin","杨文豪"));
    }

    @Test
    public void queryForPageItemsByRentedStatus() {
        HouseDao houseDao = new HouseDaoImpl();
        List<HouseStatus> houseStatuses = houseDao.queryForPageItemsByRentedStatus(0, 15, "admin", "闵海");
        for (HouseStatus houseStatus: houseStatuses
             ) {
            System.out.println(houseStatus);
        }
    }

    @Test
    public void queryAllHouseCount() {
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.queryAllHouseCount("admin"));
    }

    @Test
    public void testQueryHouseByLandlordAndStatusToCreateBill_Already() {
        HouseDao houseDao = new HouseDaoImpl();
        List<House> houses = houseDao.queryHouseByLandlordAndStatusToCreateBill_Already("admin");
        System.out.println(houses);
    }

    @Test
    public void queryMonthRentedHouseByLandlord() {
        HouseDao houseDao = new HouseDaoImpl();
        Integer integer = houseDao.queryMonthRentedHouseByLandlord("admin", "2020", "3");
        System.out.println(integer);
    }
}