package com.facishare.contract.core.storage.model;

import com.facishare.contract.core.model.ContractPro;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Aaron on 16/4/19.
 */
@Entity(value = "CustomerContract", noClassnameStored = true)
@Indexes(
        {
                @Index(fields = {@Field(ContractFields.sourceName)}, options = @IndexOptions(name = "SN_1")),
                @Index(fields = {@Field(ContractFields.sourcePhone)}, options = @IndexOptions(name = "SP_1")),
                @Index(fields = {
                        @Field(ContractFields.deviceId),
                        @Field(ContractFields.contractId),
                        @Field(ContractFields.name),
                        @Field(ContractFields.enterpriseAccount),
                        @Field(ContractFields.employeeId)
                }, options = @IndexOptions(name = "DID_CID_N_EA_E")),
        }
)
public class Contract {
    @Id
    private ObjectId id;
    @Property(ContractFields.sourceName)
    private String sourceName;
    @Property(ContractFields.enterpriseAccount)
    private String enterpriseAccount;
    @Property(ContractFields.enterpriseId)
    private int enterpriseId;
    @Property(ContractFields.employeeId)
    private int employeeId;
    @Property(ContractFields.sourcePhone)
    private String sourcePhone;
    @Property(ContractFields.deviceId)
    private String deviceId;
    @Property(ContractFields.contractId)
    private String contractId;
    @Property(ContractFields.deleted)
    private int deleted;
    @Property(ContractFields.name)
    private String name;
    @Property(ContractFields.company)
    private String company;
    @Property(ContractFields.title)
    private String title;
    /**
     * 联系次数，android上传，ios不传， 可用于分析联系人圈子
     */
    @Property(ContractFields.timesContacted)
    private String timesContacted;
    @Embedded(ContractFields.phoneNumbers)
    private List<String> phoneNumbers;
    /**
     * 数组	电子邮箱列表，不区分类型
     */
    @Embedded(ContractFields.emails)
    private List<String> emails;
    /**
     * IM列表，拼接类型比如 qq:12345, 类型从系统中读取，不做翻译
     */
    @Embedded(ContractFields.ims)
    private List<String> ims;
    /**
     * 数组	联系地址列表，不区分类型
     */
    @Embedded(ContractFields.addresses)
    private List<String> addresses;
    @Property(ContractFields.createDate)
    private Date createDate = new Date();
    @Property(ContractFields.updateDate)
    private Date updateDate;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getSourcePhone() {
        return sourcePhone;
    }

    public void setSourcePhone(String sourcePhone) {
        if (sourcePhone == null) {
            sourcePhone = "";
        }
        this.sourcePhone = sourcePhone;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        if (sourceName == null) {
            sourceName = "";
        }
        this.sourceName = sourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            name = "";
        }
        this.name = name;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (company == null) {
            company = "";
        }
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            title = "";
        }
        this.title = title;
    }

    public String getTimesContacted() {
        return timesContacted;
    }

    public void setTimesContacted(String timesContacted) {
        if (timesContacted == null) {
            timesContacted = "0";
        }
        this.timesContacted = timesContacted;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        if (phoneNumbers == null) {
            phoneNumbers = Lists.newArrayList();
        }
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        if (addresses == null) {
            addresses = Lists.newArrayList();
        }
        this.addresses = addresses;
    }

    public List<String> getIms() {
        return ims;
    }

    public void setIms(List<String> ims) {
        if (ims == null) {
            ims = Lists.newArrayList();
        }
        this.ims = ims;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        if (emails == null) {
            emails = Lists.newArrayList();
        }
        this.emails = emails;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Contract{" +
                ", sourceName='" + sourceName + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", sourcePhone='" + sourcePhone + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", contractId='" + contractId + '\'' +
                ", deleted=" + deleted +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", timesContacted='" + timesContacted + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", emails=" + emails +
                ", ims=" + ims +
                ", addresses=" + addresses +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public static Contract from(ContractPro contractPro) {
        Contract contract = new Contract();
        contract.setContractId(contractPro.getContractId());
        contract.setDeleted(contractPro.getDeleted());
        contract.setName(contractPro.getName());
        contract.setCompany(contractPro.getCompany());
        contract.setTitle(contractPro.getTitle());
        contract.setTimesContacted(contractPro.getTimesContacted());
        contract.setPhoneNumbers(contractPro.getPhoneNumbers());
        contract.setEmails(contractPro.getEmails());
        contract.setIms(contractPro.getIms());
        contract.setAddresses(contractPro.getAddresses());
        contract.setUpdateDate(new Date());
        return contract;
    }

}
