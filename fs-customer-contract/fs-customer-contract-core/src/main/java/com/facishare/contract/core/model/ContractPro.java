package com.facishare.contract.core.model;

/**
 * Created by Aaron on 16/4/20.
 */

import com.facishare.contract.core.storage.model.ContractFields;
import com.google.gson.annotations.SerializedName;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

/**
 * 原始的Contract ,来自己终端的数据参数
 */
public class ContractPro {
    /**
     * 联系人唯一id，android 基本可以保证不变，ios据说qq助手可以导致他变化？
     */
    @SerializedName("M1")
    private String contractId;
    /**
     * int（1/0）	是否被删除
     */
    @SerializedName("M2")
    private int deleted;
    @SerializedName("M3")
    private String name;
    @SerializedName("M4")
    private String company;
    @SerializedName("M5")
    private String title;
    /**
     * 联系次数，android上传，ios不传， 可用于分析联系人圈子
     */
    @SerializedName("M6")
    private String timesContacted;
    @SerializedName("M7")
    private List<String> phoneNumbers;
    /**
     * 数组	电子邮箱列表，不区分类型
     */
    @SerializedName("M8")
    private List<String> emails;
    /**
     * IM列表，拼接类型比如 qq:12345, 类型从系统中读取，不做翻译
     */
    @SerializedName("M9")
    private List<String> ims;
    /**
     * 数组	联系地址列表，不区分类型
     */
    @SerializedName("M10")
    private List<String> addresses;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimesContacted() {
        return timesContacted;
    }

    public void setTimesContacted(String timesContacted) {
        this.timesContacted = timesContacted;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getIms() {
        return ims;
    }

    public void setIms(List<String> ims) {
        this.ims = ims;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
