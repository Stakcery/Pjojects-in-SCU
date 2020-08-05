package com.renhouse.dao.impl;

import com.renhouse.dao.BaseDao;
import com.renhouse.dao.HouseDao;
import com.renhouse.pojo.House;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.HouseStatus;
import com.renhouse.pojo.vo.NearDateHouse;
import com.renhouse.pojo.vo.TenantMaintenanceFee;

import java.util.List;

public class HouseDaoImpl extends BaseDao implements HouseDao {
    @Override
    public List<House> queryHouseByLandlord(String landlord) {
        String sql = "select `id`,`landlord`,`tenant`,`monthRent`,`space`,`rentalStatus`,`address`,`layout`,`endTime`,`startTime`,`houseName`,`maintenanceFee` from t_house where landlord = ?";
        return queryForList(House.class, sql, landlord);
    }

    @Override
    public List<House> queryHouseByLandlordAndTenant(String landlord, String tenant) {
        String sql = "select `id`,`landlord`,`tenant`,`monthRent`,`space`,`rentalStatus`,`address`,`layout`,`endTime`,`startTime`,`houseName`,`maintenanceFee` from t_house where landlord = ? and tenant = ?";
        return queryForList(House.class, sql,landlord,tenant);
    }

    @Override
    public int addHouse(House house) {
        String sql = "INSERT INTO t_house(landlord,tenant,monthRent,space,rentalStatus,address,layout,endTime,startTime,houseName,`maintenanceFee`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        return update(sql,house.getLandlord(),house.getTenant(),house.getMonthRent(),house.getSpace(),house.getRentalStatus(),house.getAddress(),house.getLayout(),house.getEndTime(),house.getStartTime(),house.getHouseName(),house.getMaintenanceFee());
    }

    @Override
    public int deleteHouseById(Integer id) {
        String sql = "DELETE FROM t_house WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public int updateHouse(House house) {
        String sql = "UPDATE t_house SET landlord = ?,tenant = ?,monthRent = ?,space = ?,rentalStatus = ?,address = ?,layout = ?,startTime = ?,endTime = ?,houseName =?,maintenanceFee=? WHERE id = ?";
        return update(sql, house.getLandlord(), house.getTenant(), house.getMonthRent(), house.getSpace(), house.getRentalStatus(), house.getAddress(), house.getLayout(), house.getStartTime(), house.getEndTime(), house.getHouseName(), house.getMaintenanceFee(), house.getId());
    }

    @Override
    public House queryHouseById(Integer id) {
        String sql = "select `id`,`landlord`,`tenant`,`monthRent`,`space`,`rentalStatus`,`address`,`layout`,`endTime`,`startTime`,`houseName`,`maintenanceFee` from t_house where id = ?";
        return queryForOne(House.class, sql, id);
    }

    @Override
    public List<House> queryHouses() {
        String sql = "select * from t_house";
        return queryForList(House.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(id) from t_house";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<House> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` ,`landlord`,`tenant`,`monthRent`,`space`,`rentalStatus`,`address`,`layout`,`endTime`,`startTime`,`houseName`,`maintenanceFee` from t_house limit ?,?";
        return queryForList(House.class, sql, begin, pageSize);
    }

    @Override
    public List<HouseStatus> queryHouseByLandlordAndStatus_Already(String landlord) {
        String sql = "select tenantName,realName,address,houseName,phoneNumber,email from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁'";
        return queryForList(HouseStatus.class, sql, landlord);
    }

    @Override
    public List<House> queryHouseByLandlordAndStatusToCreateBill_Already(String landlord) {
        String sql = "select id,landlord,tenant,monthRent,startTime,endTime,houseName,maintenanceFee from t_house where landlord = ? and startTime is not NULL and endTime is not null and rentalStatus = '已租赁'";
        return queryForList(House.class, sql, landlord);
    }

    @Override
    public Integer queryHouseByLandlordAndStatusAlreadyCount(String landlord) {
        String sql = "select count(*) from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁'";
        Number count = (Number) queryForSingleValue(sql, landlord);
        return count.intValue();
    }

    @Override
    public Integer queryHouseByLandlordAndStatusAlreadyCount(String landlord, String realName) {
        String sql = "select count(*) from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and realName = ? and t_house.rentalStatus = '已租赁'";
        Number count = (Number) queryForSingleValue(sql, landlord, realName);
        return count.intValue();
    }


    @Override
    public List<House> queryForPageItemsByLandlord(int begin, int pageSize, String username) {
        String sql = "select `id` ,`landlord`,`tenant`,`monthRent`,`space`,`rentalStatus`,`address`,`layout`,`endTime`,`startTime`,`houseName`,`maintenanceFee` from t_house  where landlord=? limit ?,?";
        return queryForList(House.class, sql, username, begin, pageSize);
    }

    @Override
    public List<HouseStatus> queryForPageItemsByRentedStatus(int begin, int pageSize, String username) {
        String sql = "select tenantName,realName,address,houseName,phoneNumber,email from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁' limit ?,?";
        return queryForList(HouseStatus.class, sql, username, begin, pageSize);
    }

    @Override
    public List<HouseStatus> queryForPageItemsByRentedStatus(int begin, int pageSize, String username, String realName) {
        String sql = "select tenantName,realName,address,houseName,phoneNumber,email from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and realName = ? and t_house.rentalStatus = '已租赁' limit ?,?";
        return queryForList(HouseStatus.class, sql, username, realName, begin, pageSize);
    }

    @Override
    public Integer queryMaintenanceFeeByLandlordCount(String username) {
        String sql = "select count(*) from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁'";
        Number count = (Number) queryForSingleValue(sql, username);
        return count.intValue();
    }

    @Override
    public Integer queryMaintenanceFeeByLandlordCount(String username, String tenant) {
        String sql = "select count(*) from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁' and tenantName = ?";
        Number count = (Number) queryForSingleValue(sql, username, tenant);
        return count.intValue();
    }

    @Override
    public List<TenantMaintenanceFee> queryPagesForMaintenanceFeeByLandlordAndTenant(int begin, int pageSize, String username) {
        String sql = "select t_house.id,tenantName,realName,address,houseName,phoneNumber,maintenanceFee from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁'  limit ?,?";
        return queryForList(TenantMaintenanceFee.class, sql, username, begin, pageSize);
    }

    @Override
    public List<TenantMaintenanceFee> queryPagesForMaintenanceFeeByLandlordAndTenant(int begin, int pageSize, String username, String tenant) {
        String sql = "select t_house.id,tenantName,realName,address,houseName,phoneNumber,maintenanceFee from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁' and tenantName = ? limit ?,?";
        return queryForList(TenantMaintenanceFee.class, sql, username, tenant, begin, pageSize);
    }

    @Override
    public Integer queryForUnRentedHouseCount(String username) {
        String sql = "select count(*) from t_house  where landlord=? and rentalStatus = '未租赁'";
        Number count = (Number) queryForSingleValue(sql, username);
        return count.intValue();
    }

    @Override
    public List<House> queryForUnRentedHouseItems(int begin, int pageSize, String username) {
        String sql = "select `id` ,`landlord`,`tenant`,`monthRent`,`space`,`address`,`layout`,`endTime`,`startTime`,`houseName`,`maintenanceFee` from t_house  where landlord=? and rentalStatus = '未租赁' limit ?,?";
        return queryForList(House.class, sql, username, begin, pageSize);
    }

    @Override
    public Integer queryForNearDateCount(String username) {
        String sql = "select count(*) from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁' and datediff(endTime,CURRENT_DATE())<16";
        Number count = (Number) queryForSingleValue(sql, username);
        return count.intValue();
    }

    @Override
    public List<NearDateHouse> queryForNearDateItems(int begin, int pageSize, String username) {
        String sql = "select t_house.id,endTime,realName,address,houseName,phoneNumber,email from t_house,t_tenant where t_house.landlord =t_tenant.landlord AND t_house.tenant = t_tenant.tenantName AND t_tenant.landlord = ? and t_house.rentalStatus = '已租赁' and datediff(endTime,CURRENT_DATE())<16 limit ?,?";
        return queryForList(NearDateHouse.class,sql,username, begin,pageSize);
    }

    @Override
    public Integer queryAllHouseCount(String username) {
        String sql = "select count(*) from t_house where landlord = ?";
        Number count = (Number) queryForSingleValue(sql, username);
        return count.intValue();
    }

    @Override
    public Integer queryMonthRentedHouseByLandlord(String username, String year, String month) {
        if (month.length()==1){
            month = "0" + month;
        }
        String sql = "select count(*) from t_house where landlord = ? and rentalStatus='已租赁' and startTime Like '"+year+"-"+month+"-%'";
        Number count = (Number) queryForSingleValue(sql, username);
        return count.intValue();
    }
}
