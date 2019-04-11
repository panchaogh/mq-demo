package com.pcgrw.mqdemo.rabbitmq.route;

import com.pcgrw.mqdemo.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender {
    public static final String EXCHANGE_NAME = "testroute";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机 定义一个交换机，类型是direct，也就是路由模式
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        for (int i = 0; i < 1; i++) {
            //发布订阅模式的，因为消息是先发送到交换机中，而交换机是没有保存功能的，所以如果没有消费者，消息会丢失
            channel.basicPublish(EXCHANGE_NAME, "key1", null, ("route模式的消息" + i).getBytes());
            channel.basicPublish(EXCHANGE_NAME, "key2", null, ("route模式的消息" + i).getBytes());
            channel.basicPublish(EXCHANGE_NAME, "key3", null, ("route模式的消息" + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
