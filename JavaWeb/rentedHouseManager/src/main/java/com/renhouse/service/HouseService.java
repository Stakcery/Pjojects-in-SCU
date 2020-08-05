package com.renhouse.service;

import com.renhouse.pojo.House;
import com.renhouse.pojo.Page;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.HouseStatus;
import com.renhouse.pojo.vo.NearDateHouse;
import com.renhouse.pojo.vo.TenantMaintenanceFee;

import java.util.List;
import java.util.Map;

public interface HouseService {
    /**
     * 添加房屋
     * @param house
     */
    public void addHouse(House house);

    /**
     * 根据id删除房屋
     * @param id
     */
    public void deleteHouseById(Integer id);

    /**
     * 更新房屋信息
     * @param house
     */
    public void updateHouse(House house);

    /**
     * 根据id查房屋
     * @param id
     * @return
     */
    public House queryHouseById(Integer id);

    /**
     * 查所有房屋
     * @return
     */
    public List<House> queryHouses();

    /**
     * 得到分页信息
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<House> page(String username, int pageNo, int pageSize);

    /**
     * 根据房东信息查询已经出租的房子
     * @return
     */
    public List<HouseStatus> queryHouseByLandlordAndStatus_Already(String landlord);

    /**
     * 分页模型-根据房东名字查询已经租赁的客户信息
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<HouseStatus> pageForRentedHouse(String username, int pageNo, int pageSize);

    /**
     * 分页模型-据房东和客户名字查询已经租赁的客户信息
     * @param username
     * @param realName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<HouseStatus> pageForRentedHouse(String username, String realName,int pageNo, int pageSize);

    /**
     * 根据房东名字查询未租赁的房屋信息
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<House> pageForUnRentedHouse(String username, int pageNo, int pageSize);

    /**
     * 通过房东名和租客名查询记录
     * @param username
     * @param tenant
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<TenantMaintenanceFee> pageForMaintenanceFee(String username, String tenant,int pageNo, int pageSize);

    /**
     * 通过房东名查询记录
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<TenantMaintenanceFee> pageForMaintenanceFee(String username,int pageNo, int pageSize);

    /**
     * 分页模型-即将到期房屋
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<NearDateHouse> pageForNearDate(String username, int pageNo, int pageSize);

    /**
     * 通过房东名和年份查询每月租赁房屋
     * @param username
     * @param year
     * @return
     */
    public Map<String,Object> queryMonthRentedHouseByLandlord(String username, String year);


}
