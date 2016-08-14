package com.facishare.contract.core.service.impl;

import com.facishare.contract.core.storage.model.Contract;
import com.facishare.contract.core.service.ContractService;
import com.facishare.contract.core.storage.ContractStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Aaron on 16/4/20.
 */
public class ContractServiceImpl implements ContractService {
    private static final Logger LOG = LoggerFactory.getLogger(ContractServiceImpl.class);
    @Autowired
    private ContractStorage contractStorage;

    @Override
    public void saveContracts(List<Contract> contractList) {
        contractList.forEach(contractPro -> {
            try {
                save(contractPro);
            } catch (Exception e) {
                LOG.error("saveContracts:{}", contractPro, e);
            }
        });
    }

    @Override
    public Contract save(Contract contract) {
        return contractStorage.save(contract);
    }

    @Override
    public List<Contract> findEnterpiseUserContract(String ea, String employeeId) {
        return contractStorage.findEnterpiseUserContract(ea, employeeId);
    }

    @Override
    public List<Contract> findUserContract(String name,String userPhone,String enterpriseAccount,String employeeId) {
        return contractStorage.findUserContract(name,userPhone,enterpriseAccount,Integer.getInteger(employeeId,0));
    }
}
