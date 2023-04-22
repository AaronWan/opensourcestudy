package com.mq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQQueueExtendTest {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        String nameServer = "10.112.41.9:9876";
        String topics = "TestQueue_wansong";
        new Thread(() -> {
            DefaultMQProducer producer = createProducer(nameServer);
            for (int i = 0; i < 10000; i++) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                send(producer, topics, new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
            }
            latch.countDown();
        }).start();
        new Thread(() -> {
            String consumerName = "SubGroupA_wansong";
            try {
                DefaultMQPushConsumer consumer = createConsumer(nameServer, consumerName, topics);
                consumer.start();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        latch.await();
    }

    private static void send(DefaultMQProducer producer, String topics, String test) {
        try {
            Message msg = new Message(topics, test.getBytes());
            producer.send(msg, (mqs, message, ss) -> {
                log.info("producer: queue size:" + mqs.size());
                return mqs.get(Math.abs(ss.hashCode()) % mqs.size());
            }, test);
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

    public static DefaultMQProducer createProducer(String nameServer) {
        DefaultMQProducer producer = new DefaultMQProducer("A");
        producer.setNamesrvAddr(nameServer);
        producer.setInstanceName(UUID.randomUUID().toString());
        producer.setRetryTimesWhenSendFailed(3);
        try {
            producer.start();
        } catch (MQClientException var2) {
            log.error("StartRocketMQConsumer Error", var2);
        }
        return producer;
    }


    public static DefaultMQPushConsumer createConsumer(String nameServer, String consumerName, String topics) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerName);
        consumer.setNamesrvAddr(nameServer);
        consumer.setConsumeThreadMin(1);
        consumer.setConsumeThreadMax(2);
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.subscribe(topics, "*");
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely() {
            @Override
            public List<MessageQueue> allocate(String consumerGroup, String currentCID, List<MessageQueue> mqAll, List<String> cidAll) {
                log.error("consumer mqAll topic: "+mqAll.get(0).getTopic()+",size: " + mqAll.size());
                return super.allocate(consumerGroup, currentCID, mqAll, cidAll);
            }
        });
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        Map<String, AtomicInteger> count = Maps.newConcurrentMap();
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> ConsumeConcurrentlyStatus.CONSUME_SUCCESS);
        return consumer;
    }
}
