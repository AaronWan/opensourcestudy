package com.mq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQConsumer {

  public static void main(String[] args) throws MQClientException, InterruptedException {
    Map<String,DefaultMQPushConsumer> consumerMap=Maps.newHashMap();
    MQConsumer mqTest = new MQConsumer();
    consumerMap.put("tag1",mqTest.createConsumer(null));
  }

  public DefaultMQPushConsumer createConsumer(String tag) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Testtest1");
     consumer.setNamesrvAddr("127.0.0.1:9876");
     consumer.setInstanceName("tag1");
     consumer.setConsumeThreadMin(1);
     consumer.setConsumeThreadMax(2);
     if(StringUtils.isEmpty(tag)){
         consumer.subscribe("SELF_TEST_TOPIC","*");
     }else{
        consumer.subscribe("SELF_TEST_TOPIC"+tag, tag);
     }
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
         try {
             System.out.println(content);
             Thread.sleep(200);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
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
