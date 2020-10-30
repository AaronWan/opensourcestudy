package com.rocketmq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQConsumerOnlyOneTest {
  private DefaultMQProducer producer;

  public static void main(String[] args) throws MQClientException {
    MQConsumerOnlyOneTest mqTest = new MQConsumerOnlyOneTest();
        mqTest.createProducer("A");

        for (int i = 0; i < 1000; i++) {
          mqTest.send("" + i, i);
        }
    mqTest.createConsumer();
  }

  private void send(String test, int index) {
    try {
      Message msg = new Message("SELF_TEST_P_GROUP2", "", test.getBytes());
      producer.send(msg, (list, message, o) -> {
        int in = (Integer) o % (list.size());
        System.out.println(in);
        return list.get(in);
      }, index);
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
    this.producer.setNamesrvAddr("172.28.0.91:9876");
    this.producer.setInstanceName(UUID.randomUUID().toString());
    this.producer.setRetryTimesWhenSendFailed(3);
    try {
      this.producer.start();
    } catch (MQClientException var2) {
      log.error("StartRocketMQConsumer Error", var2);
    }
  }


  public DefaultMQPushConsumer createConsumer() throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("SELF_TEST_C_GROUP2");
    consumer.setNamesrvAddr("172.28.0.91:9876");
    consumer.setInstanceName(UUID.randomUUID().toString());
    consumer.subscribe("SELF_TEST_P_GROUP2", "*");
    consumer.setConsumeThreadMin(2);
    consumer.setConsumeThreadMax(2);
    consumer.setConsumeMessageBatchMaxSize(1);
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
    consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
      MessageExt msg = msgs.get(0);
      String content = new String(msg.getBody());
      System.out.println("magId:" + msg.getMsgId() + "tag:" + msg.getTags() + " consume: " + content + "  resumeTimes: " + msg.getReconsumeTimes());
      return ConsumeOrderlyStatus.SUCCESS;
    });
    try {
      consumer.start();
    } catch (MQClientException var2) {
      var2.printStackTrace();
    }
    return consumer;
  }
}
