package com.facishare.contract.core.facade;

import com.facishare.contract.core.model.AuthInfo;
import com.facishare.contract.core.model.ContractZipTask;
import com.facishare.contract.core.service.ContractZipDealTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Aaron on 16/4/20.
 */
public class ContractActionImpl implements ContractAction {
    private static  final Logger LOG= LoggerFactory.getLogger(ContractActionImpl.class);
    @Autowired
    private ContractZipDealTaskManager taskManager;
    @Override
    public void saveContract(AuthInfo authInfo, byte[] datas) {
        LOG.debug("auInfo:{};Size:{}",authInfo,datas.length);
        taskManager.sendToMQ(new ContractZipTask(authInfo,datas));
    }

    public void saveContract(String mobile,String deviceId,int employeeId,int enterpriseId
            ,String employeeAccount,String enterpriseAccount,byte[] datas) {
        AuthInfo authInfo=new AuthInfo();
        authInfo.setEmployeeAccount(employeeAccount);
        authInfo.setEnterpriseId(enterpriseId);
        authInfo.setEnterpriseAccount(enterpriseAccount);
        authInfo.setEmployeeId(employeeId);
        authInfo.setDeviceId(deviceId);
        authInfo.setMobile(mobile);
        taskManager.sendToMQ(new ContractZipTask(authInfo,datas));
    }

    public void setTaskManager(ContractZipDealTaskManager taskManager) {
        this.taskManager = taskManager;
    }
}
