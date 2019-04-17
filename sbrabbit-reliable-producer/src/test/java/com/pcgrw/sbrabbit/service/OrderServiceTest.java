package com.pcgrw.sbrabbit.service;

import com.pcgrw.sbrabbit.SbrabbitReliableProducerApplicationTests;
import com.pcgrw.sbrabbit.entity.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class OrderServiceTest extends SbrabbitReliableProducerApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() throws Exception {
        Order order = new Order("20190412160000001",
                "测试创建订单",
                System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.createOrder(order);
    }
}