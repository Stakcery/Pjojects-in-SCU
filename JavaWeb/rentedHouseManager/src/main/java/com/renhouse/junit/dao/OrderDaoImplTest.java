package com.renhouse.junit.dao;

import com.renhouse.dao.OrderDao;
import com.renhouse.dao.impl.OrderDaoImpl;
import com.renhouse.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    @Test
    public void queryOrderById() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderById(4));
    }

    @Test
    public void queryOrderByOrderNo() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderByOrderNo("ajdo"));
    }

    @Test
    public void queryOrderByOrderHouse() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderByOrderHouse("双流区哈哈路1号"));
    }

    @Test
    public void queryOrderByLandLord() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderByLandLord("大猩猩"));
    }

    @Test
    public void queryOrderByTenantName() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderByTenantName("小猴子"));
    }

    @Test
    public void queryOrderByOrderTime() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderByOrderTime("2020-6-20"));
    }

    @Test
    public void addOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        String orderNo = "123457";
        String orderHouse = "双流区哈哈路1号";
        String landlord = "admin";
        String tenantName = "小猴子";
        String orderTime = "2020-5-20";
        String phoneNumber = "17745485474";
        Order order = new Order(null,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber);
        orderDao.addOrder(order);
    }

    @Test
    public void deleterOrderById() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.deleterOrderById(5));
    }

    @Test
    public void updateOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        String orderNo = "ajdo";
        String orderHouse = "双流区哈哈路1号";
        String landlord = "大猩猩";
        String tenantName = "小猴子";
        String orderTime = "2020-6-20";
        String phoneNumber = "17745485474";
        Order order = new Order(4,orderNo,orderHouse,landlord,tenantName,orderTime,phoneNumber);
        orderDao.updateOrder(order);
    }

    @Test
    public void queryOrders() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.queryOrders();
        System.out.println(orders);
        for(Order order:orders) {
            System.out.println(order);
        }
    }
}