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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 *
 */
@Slf4j
public class MQOrderTest {


  public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
    MQOrderTest mqTest = new MQOrderTest();
    DefaultMQProducer producer = mqTest.createProducer("OrderTest", "172.28.0.91:9876");
    String topic = "OrderTopic";
    mqTest.createConsumer("OrderTestConsumer", "127.0.0.1:9876", topic, "*");
    Message msg = new MessageExt();
    msg.setBody(("test:" + new SimpleDateFormat("YYYYY-MM-DD HH:mm:ss").format(new Date())).getBytes());
    msg.setTopic(topic);
    producer.send(msg);
    Thread.sleep(1000 * 3600);
  }

  private DefaultMQProducer createProducer(String group, String nameserver) {
    DefaultMQProducer producer = new DefaultMQProducer(group);
    producer.setNamesrvAddr(nameserver);
    producer.setInstanceName(UUID.randomUUID().toString());
    producer.setRetryTimesWhenSendFailed(3);
    try {
      producer.start();
    } catch (MQClientException var2) {
      log.error("StartRocketMQConsumer Error", var2);
    }
    return producer;
  }

  public DefaultMQPushConsumer createConsumer(String group, String nameserver, String topic, String tags) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
    consumer.setNamesrvAddr(nameserver);
    consumer.setInstanceName("tag1");
    consumer.setConsumeThreadMin(1);
    consumer.setConsumeThreadMax(2);
    consumer.subscribe(topic, tags);
    consumer.setConsumeMessageBatchMaxSize(1);
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
    consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
      msgs.forEach(item -> {
        MessageExt msg = msgs.get(0);
        String content = new String(msg.getBody());
        System.out.println("msg:" + msg.getMsgId() + " consume: " + content + "  resumeTimes: " + msg.getReconsumeTimes());
                throw new RuntimeException();
      });
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
