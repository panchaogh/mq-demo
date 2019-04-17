package com.pcgrw.sbrabbit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.pcgrw.sbrabbit.constant.Constants;
import com.pcgrw.sbrabbit.dao.BrokerMessageLogMapper;
import com.pcgrw.sbrabbit.dao.OrderMapper;
import com.pcgrw.sbrabbit.entity.BrokerMessageLog;
import com.pcgrw.sbrabbit.entity.Order;
import com.pcgrw.sbrabbit.producer.RabbitOrderSender;
import com.pcgrw.sbrabbit.service.OrderService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;
    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Override
    public void createOrder(Order order) throws Exception {
        Date orderTime = new Date();
        //业务数据入库
        orderMapper.insert(order);
        //构建消息日志记录对象并入库
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        brokerMessageLog.setMessage(JSON.toJSONString(order));
        brokerMessageLog.setStatus(Constants.ORDER_SENDING);
        brokerMessageLog.setTryCount(0);
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLogMapper.insert(brokerMessageLog);
        //发送订单消息
        rabbitOrderSender.send(order);
    }
}
