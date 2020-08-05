package com.renhouse.junit.service;

import com.renhouse.service.OrderService;
import com.renhouse.service.impl.OrderServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    @Test
    public void deleteOrderById() {
        OrderService orderService = new OrderServiceImpl();
        orderService.deleteOrderById(2);
    }
}