package com.rocketmq.test;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.UUID;


/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class MQTest {
  private DefaultMQProducer producer;

  public static void main(String[] args) throws MQClientException, InterruptedException {
    MQTest mqTest = new MQTest();
    mqTest.createProducer();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String content = scanner.next();
      String[] temp = content.split(":");
      for (int i = 0; i < 10000; i++) {
        mqTest.send(temp[1], temp[0]);
      }
    }

  }

  private void send(String test, String tags) {
    try {
      Message msg = new Message("SELF_TEST_TOPIC" + tags, tags, test.getBytes());
      producer.send(msg, (mqs, message, ss) -> {
        return mqs.get(ss.hashCode());
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

  public void createProducer() {
    this.producer = new DefaultMQProducer("A");
    this.producer.setNamesrvAddr("10.112.41.2:9876");
    this.producer.setInstanceName(UUID.randomUUID().toString());
    this.producer.setRetryTimesWhenSendFailed(3);
    try {
      this.producer.start();
    } catch (MQClientException var2) {
      log.error("StartRocketMQConsumer Error", var2);
    }
  }
}
