package com.facishare.contract.core.storage.model;

import org.mongodb.morphia.annotations.Property;

/**
 * Created by Aaron on 16/4/20.
 */
public class Enterprise {
    @Property(EnterprseFields.enterpriseAccount)
    private String enterpriseAccount;
    @Property(EnterprseFields.employeeId)
    private String employeeId;
    @Property(EnterprseFields.enterpriseName)
    private String enterpriseName;
    public Enterprise() {
    }

    public Enterprise(String enterpriseAccount, String employeeId) {
        this.enterpriseAccount = enterpriseAccount;
        this.employeeId = employeeId;
    }

    public Enterprise(String enterpriseAccount, String employeeId, String enterpriseName) {
        this.enterpriseAccount = enterpriseAccount;
        this.employeeId = employeeId;
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "enterpriseAccount='" + enterpriseAccount + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
