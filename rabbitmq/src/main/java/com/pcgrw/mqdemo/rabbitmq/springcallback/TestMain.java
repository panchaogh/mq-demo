package com.pcgrw.mqdemo.rabbitmq.springcallback;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContextCallBack.xml"})
public class TestMain {
    public static final String EXCHANGE = "directExchange";
    public static final String QUEUE = "confirmQueue";
    @Autowired
    private PublishUtils publishUtils;

    /**
     * EXCHANGE QUEUE都正确时，ConfirmCallbackListener会执行，ack=true
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        String message = "asdf" + System.currentTimeMillis();
        publishUtils.send(EXCHANGE, QUEUE, message);
        Thread.sleep(2000);
    }

    /**
     * EXCHANGE错误 QUEUE正确时，ConfirmCallbackListener会执行，ack=false
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String message = "asdf" + System.currentTimeMillis();
        publishUtils.send(EXCHANGE + "1", QUEUE, message);
        Thread.sleep(2000);
    }

    /**
     * EXCHANGE正确 QUEUE错误时，ConfirmCallbackListener会执行，ack=true;ReturnCallbackListener会执行
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        String message = "asdf" + System.currentTimeMillis();
        publishUtils.send(EXCHANGE, QUEUE + "1", message);
        Thread.sleep(2000);
    }

    /**
     * EXCHANGE错误 QUEUE错误时，ConfirmCallbackListener会执行，ack=false
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        String message = "asdf" + System.currentTimeMillis();
        publishUtils.send(EXCHANGE + "1", QUEUE + "1", message);
        Thread.sleep(2000);
    }
}
