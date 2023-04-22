package com.mq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/6/1
 * @creat_time: 09:23
 * @since 7.5.0
 * client 4.2.6
 * server 4.2.6
 * consume apache mq 5.2
 */
@Slf4j
public class MQVersionTest {
  @SneakyThrows
  public static void main(String[] args) {
    String topic="VERSION_TEST";
    int messageCount=10000;
    new Thread(() -> createMessage(topic,messageCount)).start();
    new Thread(() -> createConsumer(topic)).start();
    Thread.sleep(1000000);
  }

  private static void createMessage(String topic,int messageCount){
    DefaultMQProducer producer = new DefaultMQProducer(topic);
    producer.setNamesrvAddr("172.28.0.234:9876");
    producer.setInstanceName(UUID.randomUUID().toString());
    producer.setVipChannelEnabled(false);
    producer.setRetryTimesWhenSendFailed(3);
    try {
      producer.start();
    } catch (MQClientException var2) {
      log.error("StartRocketMQConsumer Error", var2);
    }
    try {
      for (int i = 0; i < messageCount; i++) {
        Message msg = new Message(topic, (i+"").getBytes());
        SendResult rst = producer.send(msg);
        log.info("send Msg:{}",rst.getMsgId());
      }
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

  @SneakyThrows
  private static void createConsumer(String topic) {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(topic+"_CONSUMER");
    consumer.setNamesrvAddr("172.28.0.234:9876");
    consumer.setInstanceName(UUID.randomUUID().toString());
    consumer.subscribe(topic, "*");
    consumer.setConsumeMessageBatchMaxSize(1);
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
    Map<String, AtomicInteger> count = Maps.newConcurrentMap();
    consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
      for (MessageExt msg : msgs) {
        String tempTag = msg.getTags();
        AtomicInteger tagCount = count.get(msg.getTags());
        if (tagCount == null) {
          tagCount = new AtomicInteger();
          count.put(tempTag, tagCount);
        }
        int tempCount = tagCount.incrementAndGet();
        String content = new String(msg.getBody());
        System.out.println("tag:" + msg.getTags() + " consume: " + content + "  resumeTimes: " + msg.getReconsumeTimes() + " count:" + tempCount);

      }
      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    });
    try {
      consumer.start();
    } catch (MQClientException var2) {
      var2.printStackTrace();
    }
  }

}
