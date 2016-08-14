package com.facishare.contract.core.service.impl;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.facishare.common.rocketmq.AutoConfRocketMQProcessor;
import com.facishare.contract.common.ContractZipUtils;
import com.facishare.contract.common.JsonUtils;
import com.facishare.contract.core.service.ContractZipDealProcessor;
import com.facishare.contract.core.storage.model.Contract;
import com.facishare.contract.core.model.ContractPro;
import com.facishare.contract.core.model.ContractZipTask;
import com.facishare.contract.core.storage.model.Enterprise;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Aaron on 16/4/19.
 */
public class ContractZipDealProcessorImpl implements ContractZipDealProcessor {
    private final static Logger LOG = LoggerFactory.getLogger(ContractZipDealProcessorImpl.class);
    private static final String KEY_NAME_SERVER = "NAMESERVER";
    private static final String KEY_GROUP = "GROUP";
    private static final String KEY_TOPICS = "TOPICS";
    private String configName;
    AutoConfRocketMQProcessor processor;
    @Autowired
    private ContractServiceImpl contractService;

    @PostConstruct
    public void init() {
        MessageListenerConcurrently listener = new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                processMessage(msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        };
        processor = new AutoConfRocketMQProcessor(configName, KEY_NAME_SERVER, KEY_GROUP, KEY_TOPICS, listener);
        processor.init();
    }

    protected void processMessage(List<MessageExt> messages) {
        for (MessageExt messageExt : messages) {
            ContractZipTask task = null;
            try {
                task = ContractZipTask.fromJson(new String(messageExt.getBody()));
            } catch (Exception e) {
                LOG.error("UnSerialize DataGCTask error!", e);
            }
            LOG.debug("ProcessDataGCTask begin,{}", task);
            process(task);
            LOG.debug("ProcessDataGCTask enn,{}", task);
        }
    }

    @Override
    public void process(ContractZipTask task) {
        byte[] gzip = task.getGzip();
        String content = null;
        try {
            content = ContractZipUtils.getGZipContentJson(gzip);
            Type type = new TypeToken<List<ContractPro>>() {
            }.getType();
            List<ContractPro> contractPros = JsonUtils.fromJson(content, type);
            List<Contract> contracts = Lists.newArrayList();
            contractPros.forEach(pro -> {
                Contract contract = Contract.from(pro);
                contract.setSourceName(task.getAuthInfo().getEmployeeName());
                contract.setSourcePhone(task.getAuthInfo().getMobile());
                contract.setDeviceId(task.getAuthInfo().getDeviceId());
                contract.setEnterpriseId(task.getAuthInfo().getEnterpriseId());
                contract.setEnterpriseAccount(task.getAuthInfo().getEnterpriseAccount());
                contract.setEmployeeId(task.getAuthInfo().getEmployeeId());
                //去uc中获取用户信息
                contracts.add(contract);
            });
//            保存通讯录
            contractService.saveContracts(contracts);
            LOG.debug("save contracts:" + contracts.size());
        } catch (Exception e) {
            LOG.error("ContractZipDealProcessorImpl.process:task:{},content:{}", task, content, e);
            throw e;
        }
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

}
