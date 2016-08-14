package com.facishare.warehouse.api.model.arg;


import io.protostuff.Tag;

/**
 * Created by Aaron on 15/12/15.
 */
public class NDownloadFileArg extends WarehouseBaseArg {
    @Tag(1)
    private  String ea ; // 企业帐号
    @Tag(2)
    private  String nPath ; // 文件虚拟路径
    @Tag(3)
    private  String downloadUser ; // 下载文件的用户标识。如员工：E.32；代理商：B.eif9921jnle9
    @Tag(4)
    private  String downloadSecurityGroup; // 下载业务的安全组标识

    public String getEa() {
        return ea;
    }

    public void setEa(String ea) {
        this.ea = ea;
    }

    public String getnPath() {
        return nPath;
    }

    public void setnPath(String nPath) {
        this.nPath = nPath;
    }

    public String getDownloadUser() {
        return downloadUser;
    }

    public void setDownloadUser(String downloadUser) {
        this.downloadUser = downloadUser;
    }

    public String getDownloadSecurityGroup() {
        return downloadSecurityGroup;
    }

    public void setDownloadSecurityGroup(String downloadSecurityGroup) {
        this.downloadSecurityGroup = downloadSecurityGroup;
    }
    @Override
    public String getSourceUser() {
        if(getDownloadUser()!=null&&getDownloadUser().contains("\\.")){
            String[] userDetails=getDownloadUser().split("\\.");
            if(userDetails.length>1){
                return userDetails[1];
            }
        }
        return getDownloadUser();
    }
    @Override
    public void validate() {
        if(isNullOrEmpty(ea)||isNullOrEmpty(nPath))
            throw new ValidationException();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NDownloadFileArg{");
        sb.append("ea='").append(ea).append('\'');
        sb.append(", nPath='").append(nPath).append('\'');
        sb.append(", downloadUser='").append(downloadUser).append('\'');
        sb.append(", downloadSecurityGroup='").append(downloadSecurityGroup).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
