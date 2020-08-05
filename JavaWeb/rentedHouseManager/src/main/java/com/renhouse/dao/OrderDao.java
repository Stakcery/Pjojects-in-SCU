package com.renhouse.dao;

import com.renhouse.pojo.Order;
import java.util.List;

public interface OrderDao {
    /**
     * 根据id单词查询订单
     * @param id
     * @return 查询到的订单
     */
    public Order queryOrderById(Integer id);

    /**
     * 根据订单号查询单一订单
     * @param orderNo
     * @return 查询到的订单
     */
    public Order queryOrderByOrderNo(String orderNo);

    /**
     * 根据房屋查询订单
     * @param orderHouse
     * @return 查询到的房屋的订单
     */
    public Order queryOrderByOrderHouse(String orderHouse);

    /**
     * 根据房东查询其名下的所有订单
     * @param landlord
     * @return 查询到的所有订单
     */
    public List<Order> queryOrderByLandLord(String landlord);

    /**
     * 根据租客姓名查询所有订单
     * @param tenantName
     * @return 查询到的所有订单
     */
    public List<Order> queryOrderByTenantName(String tenantName);

    /**
     * 查询指定日期的所有订单
     * @param orderTime
     * @return 查询到的所有订单
     */
    public List<Order> queryOrderByOrderTime(String orderTime);

    /**
     * 添加订单
     * @param order
     * @return 若返回-1则添加失败，返回其他值代表影响到的数据表行数
     */
    public int addOrder(Order order);

    /**
     * 删除订单
     * @param id
     * @return 若返回-1则删除失败，返回其他值代表影响到的数据表行数
     */
    public int deleterOrderById(Integer id);

    /**
     * 更新订单
     * @param order
     * @return 若返回-1则更新失败，返回其他值代表影响到的数据表行数
     */
    public int updateOrder(Order order);

    /**
     * 返回整个订单信息
     * @return
     */
    public List<Order> queryOrders();

    /**
     * 查询总共数量
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 查询分页信息
     * @param begin
     * @param pageSize
     * @return
     */
    List<Order> queryForPageItems(int begin, int pageSize);

    /**
     * 根据房东查询分页信息
     * @param begin
     * @param pageSize
     * @param username
     * @return
     */
    public List<Order> queryForPageItemsByLandlord(int begin, int pageSize, String username);

















}
