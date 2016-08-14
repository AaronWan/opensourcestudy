package com.facishare.contract.core.service;

import com.facishare.contract.core.model.ContractZipTask;

/**
 * Created by Aaron on 16/4/20.
 */
public interface ContractZipDealTaskManager {
    void sendToMQ(ContractZipTask task);

    void shutDown();
}
