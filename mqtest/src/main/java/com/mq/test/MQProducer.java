package com.mq.test;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;


/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQProducer {
  private DefaultMQProducer producer;

  public static void main(String[] args) {
    MQProducer mqTest = new MQProducer();
    mqTest.createProducer();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String content = scanner.next();
      String[] temp = content.split(":");
      for (int i = 0; i < 10000; i++) {
        mqTest.send(temp[0],temp[1]+"-"+new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
      }
    }

  }

  private void send(String tags,String test) {
    try {
      Message msg = new Message("SELF_TEST_TOPIC", tags, test.getBytes());
      producer.send(msg, (mqs, message, ss) -> mqs.get(Math.abs(ss.hashCode())%mqs.size()), test);
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
    this.producer.setRetryTimesWhenSendFailed(3);
    try {
      this.producer.start();
    } catch (MQClientException var2) {
      log.error("StartRocketMQConsumer Error", var2);
    }
  }
}
