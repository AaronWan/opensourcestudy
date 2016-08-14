package com.facishare.contract.core.service;

import com.facishare.contract.core.storage.model.Contract;

import java.util.List;

/**
 * Created by Aaron on 16/4/19.
 */
public interface ContractService {
    void saveContracts(List<Contract> contractList);

    Contract save(Contract contract);

    List<Contract> findEnterpiseUserContract(String ea, String employeeId);

    List<Contract> findUserContract(String userPhone, String name,String enterpriseAccount,String employeeId);
}
