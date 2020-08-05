package com.renhouse.dao.impl;

import com.renhouse.dao.BaseDao;
import com.renhouse.dao.OrderDao;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public Order queryOrderById(Integer id) {
        String sql = "SELECT id,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber FROM `t_order` where id = ?";
        return queryForOne(Order.class,sql,id);
    }

    @Override
    public Order queryOrderByOrderNo(String orderNo) {
        String sql = "SELECT id,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber FROM `t_order` where orderNo = ?";
        return queryForOne(Order.class,sql,orderNo);
    }

    @Override
    public Order queryOrderByOrderHouse(String orderHouse) {
        String sql = "SELECT id,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber FROM `t_order` where orderHouse = ?";
        return queryForOne(Order.class,sql,orderHouse);
    }

    @Override
    public List<Order> queryOrderByLandLord(String landlord) {
        String sql = "SELECT id,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber FROM `t_order` where landlord = ?";
        return queryForList(Order.class,sql,landlord);
    }

    @Override
    public List<Order> queryOrderByTenantName(String tenantName) {
        String sql = "SELECT id,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber FROM `t_order` where tenantName = ?";
        return queryForList(Order.class,sql,tenantName);
    }

    @Override
    public List<Order> queryOrderByOrderTime(String orderTime) {
        String sql = "SELECT id,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber FROM `t_order` where orderTime = ?";
        return queryForList(Order.class,sql,orderTime);
    }

    @Override
    public int addOrder(Order order) {
        String sql = "insert into t_order(orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber) values (?,?,?,?,?,?)";
        return update(sql,order.getOrderNo(),order.getOrderHouse()
                ,order.getLandlord(),order.getTenantName(),order.getOrderTime()
                ,order.getPhoneNumber());
    }

    @Override
    public int deleterOrderById(Integer id) {
        String sql = "DELETE FROM t_order WHERE id = ?";
        return update(sql,id);
    }

    @Override
    public int updateOrder(Order order) {
        String sql = "update t_order set orderNo = ?,orderHouse = ?,landlord = ?,tenantName = ?,orderTime = ?,phoneNumber = ? where id = ?";
        return update(sql,order.getOrderNo(),order.getOrderHouse(),order.getLandlord(),order.getTenantName(),order.getOrderTime()
                ,order.getPhoneNumber(),order.getId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `orderNo`,`orderHouse`,`landlord`,`tenantName`,`orderTime`,`phoneNumber` from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(id) from t_order";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Order> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` ,`orderNo`,`orderHouse`,`landlord`,`tenantName`,`orderTime`,`phoneNumber` from t_order limit ?,?";
        return queryForList(Order.class,sql,begin,pageSize);
    }

    @Override
    public List<Order> queryForPageItemsByLandlord(int begin, int pageSize, String username) {
        String sql = "select `id` ,`orderNo`,`orderHouse`,`landlord`,`tenantName`,`orderTime`,`phoneNumber` from t_order  where landlord=? limit ?,?";
        return queryForList(Order.class,sql,username, begin,pageSize);
    }
}
