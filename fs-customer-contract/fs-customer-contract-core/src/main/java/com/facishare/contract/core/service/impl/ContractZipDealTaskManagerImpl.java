package com.facishare.contract.core.service.impl;

import com.alibaba.rocketmq.common.message.Message;
import com.facishare.common.rocketmq.AutoConfRocketMQSender;
import com.facishare.contract.core.model.ContractZipTask;
import com.facishare.contract.core.service.ContractZipDealTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Created by Aaron on 16/4/20.
 */
public class ContractZipDealTaskManagerImpl implements ContractZipDealTaskManager {
    private static final Logger LOG = LoggerFactory.getLogger(ContractZipDealTaskManagerImpl.class);

    private String configName = "";
    private static final String KEY_NAME_SERVER = "NAMESERVER";
    private static final String KEY_GROUP = "GROUP";
    private static final String KEY_TOPICS = "TOPICS";

    private AutoConfRocketMQSender sender;

    @PostConstruct
    public void init() {
        sender = new AutoConfRocketMQSender(configName, KEY_NAME_SERVER, KEY_GROUP, KEY_TOPICS);
        sender.init();
    }

    @Override
    public void sendToMQ(ContractZipTask task) {
        try {
            Message message = new Message(null, task.toJson().getBytes());
            sender.send(message);
        } catch (Exception e) {
            LOG.error("SendContractZipTask:{} Error!", task, e);
        }
    }

    @Override
    public void shutDown() {
        if (sender != null) {
            sender.shutDown();
        }
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
