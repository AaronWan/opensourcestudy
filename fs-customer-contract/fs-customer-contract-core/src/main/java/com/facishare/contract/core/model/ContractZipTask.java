package com.facishare.contract.core.model;

import com.facishare.contract.common.JsonUtils;

/**
 * Created by Aaron on 16/4/19.
 */
public class ContractZipTask {
    private AuthInfo authInfo;
    private byte[] gzip;

    public ContractZipTask(AuthInfo authInfo, byte[] gzip) {
        this.authInfo=authInfo;
        this.gzip = gzip;
    }

    public byte[] getGzip() {
        return gzip;
    }

    public void setGzip(byte[] gzip) {
        this.gzip = gzip;
    }

    public String toJson(){
        return JsonUtils.toJson(this);
    }

    public static ContractZipTask fromJson(String json)
    {
        return JsonUtils.fromJson(json,ContractZipTask.class);
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        return "ContractZipTask{" +
                "authInfo=" + authInfo +
                ", gzip='" + gzip.length + '\'' +
                '}';
    }
}
