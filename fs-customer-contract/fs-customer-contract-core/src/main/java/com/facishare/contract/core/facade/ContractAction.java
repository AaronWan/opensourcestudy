package com.facishare.contract.core.facade;

import com.facishare.contract.core.model.AuthInfo;

/**
 * Created by Aaron on 16/4/20.
 */
public interface ContractAction {
    void saveContract(AuthInfo authInfo, byte[] datas);

    void saveContract(String mobile,String deviceId,int employeeId,int enterpriseId
            ,String employeeAccount,String enterpriseAccount,byte[] datas);
}
