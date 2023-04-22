package com.mq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQMutilTopicsTest {
  private DefaultMQProducer producer;

  public static void main(String[] args) throws MQClientException, InterruptedException {
    MQMutilTopicsTest mqTest = new MQMutilTopicsTest();
    mqTest.createProducer("A");
    mqTest.createConsumer();
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNext()) {
      String content = scanner.next();
      String[] temp = content.split(":");
      for (int i = 0; i < 1; i++) {
        mqTest.send(temp[1], temp[0]);
      }
    }

  }

  private void send(String test, String tags) {
    try {
      Message msg = new Message("SELF_TEST_TOPIC"+tags, tags, test.getBytes());
      producer.send(msg);
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

  public void createProducer(String group) {
    this.producer = new DefaultMQProducer(group);
    this.producer.setNamesrvAddr("127.0.0.1:9876");
    this.producer.setInstanceName(UUID.randomUUID().toString());
    this.producer.setRetryTimesWhenSendFailed(3);
    try {
      this.producer.start();
    } catch (MQClientException var2) {
      log.error("StartRocketMQConsumer Error", var2);
    }
  }


  public DefaultMQPushConsumer createConsumer() throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Testtest1");
    consumer.setNamesrvAddr("127.0.0.1:9876");
    consumer.setInstanceName("tag1");
    consumer.setConsumeThreadMin(1);
    consumer.setConsumeThreadMax(2);
    consumer.subscribe("SELF_TEST_TOPICtag1", "tag1");
    consumer.subscribe("SELF_TEST_TOPICtag2", "tag2");
    consumer.setConsumeMessageBatchMaxSize(1);
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
    Map<String, AtomicInteger> count = Maps.newConcurrentMap();
    consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
      MessageExt msg = msgs.get(0);
      String tempTag = msg.getTags();
      AtomicInteger tagCount = count.get(msg.getTags());
      if (tagCount == null) {
        tagCount = new AtomicInteger();
        count.put(tempTag, tagCount);
      }
      int tempCount = tagCount.incrementAndGet();
      String content = new String(msg.getBody());
        System.out.println("magId:"+msg.getMsgId()+"tag:" + msg.getTags() + " consume: " + content + "  resumeTimes: " + msg.getReconsumeTimes() + " count:" + tempCount);
      return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    });
    try {
      consumer.start();
    } catch (MQClientException var2) {
      var2.printStackTrace();
    }
    return consumer;
  }
}
