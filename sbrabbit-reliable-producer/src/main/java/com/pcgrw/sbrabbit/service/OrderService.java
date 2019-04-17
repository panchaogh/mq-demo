package com.pcgrw.sbrabbit.service;

import com.pcgrw.sbrabbit.entity.Order;

public interface OrderService {
    /**
     * 生成订单
     *
     * @param order
     * @throws Exception
     */
    void createOrder(Order order) throws Exception;
}
