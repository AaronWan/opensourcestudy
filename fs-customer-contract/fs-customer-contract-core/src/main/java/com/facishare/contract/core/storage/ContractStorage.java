package com.facishare.contract.core.storage;

import com.facishare.contract.core.storage.model.Contract;

import java.util.List;

/**
 * Created by Aaron on 16/4/20.
 */
public interface ContractStorage {
    Contract save(Contract contract);

    List<Contract> findUserContract(String name,String userPhone,String enterpriseAccount,int employeeId);

    List<Contract> findEnterpiseUserContract(String ea, String employeeId);

    Contract findOne(String deviceId,String contractId,String ea,int employeeId);

    int removeOne(String deviceId, String contractId, String ea, int employee);

    int remove(String deviceId, String contractId);

    int remove(String deviceId);
}
