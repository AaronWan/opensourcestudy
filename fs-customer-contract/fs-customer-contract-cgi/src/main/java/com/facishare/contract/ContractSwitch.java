package com.facishare.contract;

import com.facishare.contract.rest.ContractServiceImpl;
import com.github.autoconf.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Aaron on 16/4/21.
 */
@Component
public class ContractSwitch {
    public static volatile boolean authAble = false;
    Logger LOG= LoggerFactory.getLogger(ContractSwitch.class);
    @Autowired
    ContractServiceImpl contractService;
    @PostConstruct
    public void init()
    {
        ConfigFactory.getInstance().getConfig("fs-contract-switch", config -> {
            LOG.info("======================start fs-contract-switch======================");
            authAble = config.getBool("auth.able", false);
            LOG.info("auth.able:"+authAble);
            contractService.setAuthAble(authAble);
            LOG.info("======================end fs-contract-switch======================");
        });
    }

}
