package com.facishare.contract.core.storage;

import com.facishare.contract.core.storage.impl.ContractStorageImpl;
import com.facishare.contract.core.storage.model.Contract;
import com.facishare.contract.core.storage.model.Enterprise;
import com.github.mongo.support.MongoDataStoreFactoryBean;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * Created by Aaron on 16/4/20.
 */

public class ContractDaoImplTest {
    private MongoDataStoreFactoryBean mongoTemplate;
    private ContractStorageImpl contractStorage;

    @Before
    public void init() throws Exception {
        mongoTemplate = new MongoDataStoreFactoryBean();
        mongoTemplate.setConfigName("fs-contract-mongo-config");
        mongoTemplate.afterPropertiesSet();
        contractStorage = new ContractStorageImpl();
        contractStorage.setDatastoreExt(mongoTemplate.getObject());

    }

    @Test
    public void save() {
        Contract contract = createContract();
        contract = contractStorage.findOne(contract.getDeviceId(), contract.getContractId(), contract.getEnterpriseAccount(), contract.getEmployeeId());
        Assert.assertNotNull(contract);
    }

    @Test
    public void findOne() {
        String companyName = "test";
        Contract contract = createContract();
        Assert.assertNotNull(contractStorage.findOne(contract.getDeviceId(), contract.getContractId(), contract.getEnterpriseAccount(), contract.getEmployeeId()));
        contract.setCompany(companyName);
        contract = contractStorage.save(contract);
        Assert.assertEquals(companyName, contract.getCompany());
        contract.setEmployeeId(22);
        contract.setEnterpriseAccount("xxx");
        contract.setEnterpriseId(44);
        contract.setId(null);
        contract = contractStorage.save(contract);
        List<Contract> contracts = contractStorage.findUserContract(contract.getSourceName(), null, null, 0);
        System.out.println(contracts.size() );
        Assert.assertTrue(contracts.size() == 2);
    }

    @Test
    public void findContract() {
        Contract contract = createContract();
        Assert.assertNotNull(contractStorage.findUserContract(contract.getSourceName(), null, null, 0));
    }

    String deviceId = UUID.randomUUID().toString();

    public Contract createContract() {
        Contract contract = new Contract();
        String sourceName = deviceId;
        String sourcePhone = "14110120301";
        contract.setContractId(sourceName);
        contract.setSourceName(sourceName);
        contract.setSourcePhone(sourcePhone);
        contract.setDeviceId(deviceId);
        contract = contractStorage.save(contract);
        return contract;
    }

    @After
    public void destory() {
        contractStorage.remove(deviceId);
    }
}
