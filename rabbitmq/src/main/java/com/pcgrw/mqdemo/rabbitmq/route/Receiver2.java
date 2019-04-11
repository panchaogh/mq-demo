package com.pcgrw.mqdemo.rabbitmq.route;

import com.pcgrw.mqdemo.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Receiver2 {
    public static final String EXCHANGE_NAME = "testroute";
    public static final String QUEUE_TEST2 = "testrouqueue2";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        /*声明队列，如果队列存在，什么都不做；如果队列不存在，则创建队列
        参数一：队列名称
        参数二：是否持久化队列，我们的队列模式是在内存中的，如果rabbitmq重启队列中的数据会丢失，如果我们设置为true,则会将数据保存到erlang自带的数据库中，重气后会重新读取
        参数三：是否排外，有两个作用，作用一 当我们连接关闭后是否会自动删除队列，作用二 是否私有当前队列，如果私有了，其他通道不可以访问当前队列，如果为true，一般是一个队列只适用于一个消费者的时候
        参数四：是否自动删除
        参数五：我们的一些其他参数
         */
        channel.queueDeclare(QUEUE_TEST2, false, false, false, null);
        //绑定队列到交换机 参数3 标记，绑定到交换机的时候，指定一个标记，只有和他一样的标记的消息才会被当前消费者消费
        channel.queueBind(QUEUE_TEST2,EXCHANGE_NAME,"key1");
        //如果要接收多个标记，只需要在执行一次即可
        channel.queueBind(QUEUE_TEST2,EXCHANGE_NAME,"key3");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //当我们收到消息时调用
                System.out.println("消息者2收到消息内容："+new String(body));
                //确认 参数二 false为确认收到消息 true为拒绝收到消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //接收消息，参数2是 手动确认，我们收到消息后需要手动告诉服务器，我们收到消息了
        channel.basicConsume(QUEUE_TEST2, false, consumer);
    }
}
