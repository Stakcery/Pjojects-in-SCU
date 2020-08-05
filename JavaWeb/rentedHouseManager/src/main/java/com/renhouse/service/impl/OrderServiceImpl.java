package com.renhouse.service.impl;

import com.renhouse.dao.HouseDao;
import com.renhouse.dao.OrderDao;
import com.renhouse.dao.impl.HouseDaoImpl;
import com.renhouse.dao.impl.OrderDaoImpl;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Order;
import com.renhouse.pojo.Page;
import com.renhouse.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void deleteOrderById(Integer id) {
        orderDao.deleterOrderById(id);
    }

    @Override
    public Order queryOrderById(Integer id) {
        return orderDao.queryOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

    @Override
    public Page<Order> page(String username, int pageNo, int pageSize) {
        Page<Order> page = new Page<Order>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = orderDao.queryForPageTotalCount();
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
        List<Order> items = orderDao.queryForPageItemsByLandlord(begin,pageSize,username);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
