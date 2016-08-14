package com.facishare.contract.core.storage.impl;

import com.facishare.contract.core.storage.model.Contract;
import com.facishare.contract.core.storage.ContractStorage;
import com.facishare.contract.core.storage.model.ContractFields;
import com.facishare.contract.core.storage.model.EnterprseFields;
import com.github.mongo.support.DatastoreExt;
import com.google.common.base.Strings;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by Aaron on 16/4/20.
 */
public class ContractStorageImpl implements ContractStorage {
    private final Logger LOG = LoggerFactory.getLogger(ContractStorageImpl.class);
    private DatastoreExt datastoreExt;

    public Contract save(Contract contract) {
        Contract current = findOne(contract.getDeviceId(), contract.getContractId(), contract.getEnterpriseAccount(), contract.getEmployeeId());
        if (current != null) {
            UpdateOperations<Contract> updateOperations = datastoreExt.createUpdateOperations(Contract.class);
            if (contract.getDeleted() == 1) {
                updateOperations.set(ContractFields.deleted, contract.getDeleted());
            } else {
                if (!Strings.isNullOrEmpty(contract.getCompany())) {
                    updateOperations.set(ContractFields.company, contract.getCompany());
                }
                if (!Strings.isNullOrEmpty(contract.getTitle())) {
                    updateOperations.set(ContractFields.title, contract.getTitle());
                }
                if (!Strings.isNullOrEmpty(contract.getTimesContacted())) {
                    updateOperations.set(ContractFields.timesContacted, contract.getTimesContacted());
                }
                if (contract.getPhoneNumbers() != null && contract.getPhoneNumbers().size() != 0)
                    updateOperations.addAll(ContractFields.phoneNumbers, contract.getPhoneNumbers(), false);
                if (contract.getAddresses() != null && contract.getAddresses().size() != 0)
                    updateOperations.addAll(ContractFields.addresses, contract.getAddresses(), false);
                if (contract.getEmails() != null && contract.getEmails().size() != 0)
                    updateOperations.addAll(ContractFields.emails, contract.getEmails(), false);
                if (contract.getIms() != null && contract.getIms().size() != 0)
                    updateOperations.addAll(ContractFields.ims, contract.getIms(), false);
            }
            updateOperations.set(ContractFields.updateDate, new Date());
            datastoreExt.update(current, updateOperations);
            Contract newContract = datastoreExt.findAndModify(datastoreExt.createQuery(Contract.class)
                    .field(ContractFields.deviceId).equal(contract.getDeviceId())
                    .field(ContractFields.contractId).equal(contract.getContractId())
                    .field(ContractFields.enterpriseAccount).equal(contract.getEnterpriseAccount())
                    .field(ContractFields.employeeId).equal(contract.getEmployeeId()), updateOperations);
            LOG.debug("old:{},new:{}", current, newContract);
            return newContract;
        }
        LOG.debug("save:{}", contract);
        if (contract.getDeleted() == 1 && Strings.isNullOrEmpty(contract.getName())) {
            LOG.debug("throw:{}", contract);
            return contract;
        }
        datastoreExt.save(contract);
        return contract;
    }

    @Override
    public List<Contract> findUserContract(String name, String userPhone, String enterpriseAccount, int employeeId) {
        Query<Contract> query = datastoreExt.createQuery(Contract.class);
        if (!Strings.isNullOrEmpty(name)) {
            query.field(ContractFields.sourceName).equal(name);
        }
        if (!Strings.isNullOrEmpty(userPhone)) {
            query.field(ContractFields.sourcePhone).equal(userPhone);
        }
        if (!Strings.isNullOrEmpty(enterpriseAccount)) {
            query.field(ContractFields.enterpriseAccount).equal(enterpriseAccount);
        }
        if (employeeId > 0) {
            query.field(ContractFields.employeeId).equal(employeeId);
        }
        LOG.debug("findUserContract:name:{},userPhone:{}", name, userPhone);
        return query.asList();
    }

    @Override
    public List<Contract> findEnterpiseUserContract(String ea, String employeeId) {
        return datastoreExt.createQuery(Contract.class).field(ContractFields.enterpriseAccount).equal(ea)
                .field(ContractFields.employeeId).equal(employeeId).asList();
    }

    /**
     * 同一条通信录
     * 通信录编号
     *
     * @param deviceId
     * @param contractId
     * @param ea
     * @param employee
     * @return
     */
    @Override
    public Contract findOne(String deviceId, String contractId, String ea, int employee) {
        LOG.debug("findOne:deviceId:{},contractId:{}", deviceId, contractId);
        return datastoreExt.createQuery(Contract.class).field(ContractFields.deviceId).equal(deviceId)
                .field(ContractFields.contractId).equal(contractId)
                .field(ContractFields.enterpriseAccount).equal(ea)
                .field(ContractFields.employeeId).equal(employee).get();
    }

    @Override
    public int removeOne(String deviceId, String contractId, String ea, int employee) {
        LOG.debug("removeOne:deviceId:{},contractId:{},ea:{},employee:{}", deviceId, contractId, ea, employee);
        return datastoreExt.delete(datastoreExt.createQuery(Contract.class).field(ContractFields.deviceId).equal(deviceId)
                .field(ContractFields.contractId).equal(contractId)
                .field(ContractFields.enterpriseAccount).equal(ea)
                .field(ContractFields.employeeId).equal(employee)).getN();
    }

    @Override
    public int remove(String deviceId, String contractId) {
        LOG.debug("removeOne:deviceId:{},contractId:{}", deviceId, contractId);
        return datastoreExt.delete(datastoreExt.createQuery(Contract.class).field(ContractFields.deviceId).equal(deviceId)
                .field(ContractFields.contractId).equal(contractId)).getN();
    }


    @Override
    public int remove(String deviceId) {
        LOG.debug("removeOne:deviceId:{}", deviceId);
        return datastoreExt.delete(datastoreExt.createQuery(Contract.class).field(ContractFields.deviceId).equal(deviceId)
        ).getN();
    }

    public DatastoreExt getDatastoreExt() {
        return datastoreExt;
    }

    public void setDatastoreExt(DatastoreExt datastoreExt) {
        this.datastoreExt = datastoreExt;
        datastoreExt.ensureIndexes(true);
    }
}
