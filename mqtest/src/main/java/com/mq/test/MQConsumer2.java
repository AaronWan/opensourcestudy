package com.mq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQConsumer2 {

  public static void main(String[] args) throws MQClientException, InterruptedException {
    Map<String,DefaultMQPushConsumer> consumerMap=Maps.newHashMap();
    MQConsumer2 mqTest = new MQConsumer2();
//    mqTest.createProducer();
    consumerMap.put("tag2",mqTest.createConsumer("tag2"));
  }

  public DefaultMQPushConsumer createConsumer(String tag) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Testtest2");
     consumer.setNamesrvAddr("127.0.0.1:9876");
     consumer.setInstanceName("tag2");
     consumer.setConsumeThreadMin(1);
     consumer.setConsumeThreadMax(2);
     consumer.subscribe("SELF_TEST_TOPIC"+tag, tag);
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
      if (tempCount % 1000 == 0) {
        System.out.println("tag:" + msg.getTags() + " consume: " + content + "  resumeTimes: " + msg.getReconsumeTimes() + " count:" + tempCount);
      }

      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    });
    try {
       consumer.start();
    } catch (MQClientException var2) {
      var2.printStackTrace();
    }
    return consumer;
  }
}
