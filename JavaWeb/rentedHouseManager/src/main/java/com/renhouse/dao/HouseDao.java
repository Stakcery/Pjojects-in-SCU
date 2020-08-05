package com.renhouse.dao;

import com.renhouse.pojo.House;
import com.renhouse.pojo.vo.Bill;
import com.renhouse.pojo.vo.HouseStatus;
import com.renhouse.pojo.vo.NearDateHouse;
import com.renhouse.pojo.vo.TenantMaintenanceFee;

import java.util.List;

public interface HouseDao {

    /**
     * 根据房东查询房屋信息
     * @param landlord
     * @return 若返回null，说明没有这个房屋，反之亦然
     */
    public List<House> queryHouseByLandlord(String landlord);

    /**
     * 根据房东和租客查询房屋信息
     * @param landlord
     * @param tenant
     * @return 若返回null，说明房东或租客姓名错误，反之亦然
     */
    public List<House> queryHouseByLandlordAndTenant(String landlord,String tenant);

    /**
     * 添加房屋信息
     * @param house
     * @return
     */
    public int addHouse(House house);

    /**
     * 通过ID删除房屋信息
     * @param id
     * @return
     */
    public int deleteHouseById(Integer id);

    /**
     * 更新房屋信息
     * @param house
     * @return
     */
    public int updateHouse(House house);

    /**
     * 通过房屋ID查询房屋
     * @param id
     * @return
     */
    public House queryHouseById(Integer id);

    /**
     * 返回整个房屋信息
     * @return
     */
    public List<House> queryHouses();

    /**
     * 查询总共数量
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 根据房东信息查询其名下已经出租的房子
     * @return
     */
    public List<HouseStatus> queryHouseByLandlordAndStatus_Already(String landlord);

    /**
     * 根据房东信息查询其名下已经出租的房子来创建账单
     * @param landlord
     * @return List<Bill>
     */
    public List<House> queryHouseByLandlordAndStatusToCreateBill_Already(String landlord);

    /**
     * 得到指定房东名下已出租房子的数量
     * @param landlord
     * @return 返回值为房东名下已出租房子的数量
     */
    public Integer queryHouseByLandlordAndStatusAlreadyCount(String landlord);

    /**
     * 得到指定房东和真实客户名名下已出租房子的数量
     * @param landlord
     * @param realName
     * @return
     */
    public Integer queryHouseByLandlordAndStatusAlreadyCount(String landlord,String realName);

    /**
     * 查询分页信息
     * @param begin
     * @param pageSize
     * @return
     */
    List<House> queryForPageItems(int begin, int pageSize);

    /**
     * 根据房东信息查询每个分页有关的房子
     * @param begin
     * @param pageSize
     * @param username
     * @return 返回所有房东的房子
     */
    public List<House> queryForPageItemsByLandlord(int begin, int pageSize, String username);

    /**
     * 通过房东名和客户真实姓名得到分页所有房屋的出租状态
     * @param begin
     * @param pageSize
     * @param username
     * @return 分页所有房屋的出租状态
     */
    public List<HouseStatus> queryForPageItemsByRentedStatus(int begin, int pageSize, String username);

    /**
     * 通过房东名和客户真实姓名得到分页所有房屋的出租状态
     * @param begin
     * @param pageSize
     * @param username
     * @return 分页所有房屋的出租状态
     */
    public List<HouseStatus> queryForPageItemsByRentedStatus(int begin, int pageSize, String username,String realName);

    /**
     * 通过房东查询所有已租赁房屋相关信息
     * @param username
     * @return
     */
    public Integer queryMaintenanceFeeByLandlordCount(String username);

    /**
     * 通过房东和客户查询已租赁房屋订单数
     * @param username
     * @param tenant
     * @return
     */
    public Integer queryMaintenanceFeeByLandlordCount(String username, String tenant);

    /**
     * 通过房东查询已租赁房屋相关信息
     * @param begin
     * @param pageSize
     * @param username
     * @return
     */
    public List<TenantMaintenanceFee> queryPagesForMaintenanceFeeByLandlordAndTenant(int begin, int pageSize, String username);
    /**
     * 通过房东和客户查询已租赁房屋相关信息
     * @param begin
     * @param pageSize
     * @param username
     * @return
     */
    public List<TenantMaintenanceFee> queryPagesForMaintenanceFeeByLandlordAndTenant(int begin, int pageSize, String username,String tenant);

    /**
     * 通过房东名字查询未租赁房屋信息
     * @param username
     * @return
     */
    public Integer queryForUnRentedHouseCount(String username);

    /**
     * 未租赁房屋分页模型
     * @param begin
     * @param pageSize
     * @param username
     * @return
     */
    public List<House> queryForUnRentedHouseItems(int begin,int  pageSize,String username);

    /**
     * 查询还有十五天到期房屋数量
     * @param username
     * @return
     */
    public Integer queryForNearDateCount(String username);

    /**
     *  分页模型-查询还有十五天到期房屋数量
     * @param begin
     * @param pageSize
     * @param username
     * @return
     */
    public List<NearDateHouse> queryForNearDateItems(int begin, int  pageSize, String username);

    /**
     * 查询所有房屋数量
     * @param username
     * @return
     */
    public Integer queryAllHouseCount(String username);

    public Integer queryMonthRentedHouseByLandlord(String username, String year,String month);


}
