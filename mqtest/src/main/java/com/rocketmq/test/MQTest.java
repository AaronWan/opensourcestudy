package com.rocketmq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQTest {
    private DefaultMQPushConsumer consumer;
    private DefaultMQProducer producer;

    public static void main(String[] args) throws MQClientException, InterruptedException {
        MQTest mqTest=new MQTest();
        mqTest.createConsumer();

        Thread.sleep(1000000);
//        mqTest.createProducer();

    }

    private void send(String test) {
        try {
            producer.send(new Message("AA",test.getBytes()));
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createProducer() {
        this.producer = new DefaultMQProducer("A");
        this.producer.setNamesrvAddr("localhost:9876");
        this.producer.setInstanceName(UUID.randomUUID().toString());
        this.producer.setCreateTopicKey("ATopic");
        try {
            this.producer.start();
        } catch (MQClientException var2) {
            log.error("StartRocketMQConsumer Error", var2);
        }
    }
    public void createConsumer() throws MQClientException {
        this.consumer = new DefaultMQPushConsumer("Testtest");
        this.consumer.setNamesrvAddr("10.113.41.2:9876;10.113.41.4:9876");
        this.consumer.setInstanceName(UUID.randomUUID().toString());
        this.consumer.setConsumeThreadMin(1);
        this.consumer.setConsumeThreadMax(2);
        this.consumer.subscribe("TOPIC_CRM_OPENAPI",null);
        this.consumer.setConsumeMessageBatchMaxSize(5);
        this.consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(item->log.info(new String(item.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        try {
            this.consumer.start();
        } catch (MQClientException var2) {
            var2.printStackTrace();
        }
    }
}
