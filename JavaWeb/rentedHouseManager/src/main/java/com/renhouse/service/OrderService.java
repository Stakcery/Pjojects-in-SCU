package com.renhouse.service;

import com.renhouse.pojo.House;
import com.renhouse.pojo.Order;
import com.renhouse.pojo.Page;

public interface OrderService {
    /**
     * 通过ID删除订单
     * @param id
     */
    public void deleteOrderById(Integer id);

    /**
     * 根据id查订单
     * @param id
     * @return
     */
    public Order queryOrderById(Integer id);

    /**
     * 更新订单
     * @param order
     */
    public void updateOrder(Order order);

    /**
     * 得到订单的分页信息
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Order> page(String username, int pageNo, int pageSize);
}
