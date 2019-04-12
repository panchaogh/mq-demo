package com.pcgrw.sbrabbit.producer;

import com.pcgrw.sbrabbit.SbrabbitProducerApplicationTests;
import com.pcgrw.sbrabbit.entity.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class OrderSenderTest extends SbrabbitProducerApplicationTests {
    @Autowired
    OrderSender orderSender;

    @Test
    public void sendOrder() {
        Order order = new Order("20190412160000001",
                "测试订单1",
                System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderSender.send(order);
    }
}