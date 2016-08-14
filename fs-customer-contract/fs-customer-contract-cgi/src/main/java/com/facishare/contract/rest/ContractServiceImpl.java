package com.facishare.contract.rest;

import com.facishare.contract.common.JsonUtils;
import com.facishare.contract.core.facade.ContractAction;
import com.facishare.contract.core.model.AuthInfo;
import com.facishare.contract.rest.provider.AuthInfoType;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Aaron on 16/4/19.
 */
@Component("contractService")
@Path("EM/Contract")
public class ContractServiceImpl implements IContractService {

    private static final Logger LOG = LoggerFactory.getLogger(ContractServiceImpl.class);
    @Autowired
    private ContractAction contractAction;
    private boolean authAble=false;
    @POST
    @Path("Upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @AuthInfoType
    @Override
    public Response upload(MultipartFormDataInput input,@Context AuthInfo authInfo) {
        if (authAble) {
            if (authInfo == null) {
                return Response.status(403).entity(JsonUtils.toJson(new Result(0,"no security info"))).build();
            }
        }
        List<InputPart> datas = input.getFormDataMap().get("data");
        try {
            if(datas==null){
                return Response.status(400).entity(JsonUtils.toJson(new Result(0,"file field namd:data not exists !"))).build();
            }
            for (InputPart part : datas) {
                InputStream in = part.getBody(InputStream.class, null);
                byte[] zipData = IOUtils.toByteArray(in);
                contractAction.saveContract(authInfo, zipData);
            }
        } catch (Exception e) {
            LOG.error("upload deal error!", e);
            return Response.status(500).entity(JsonUtils.toJson(new Result(0,"upload deal error"))).build();
        }
        return Response.status(200).entity(JsonUtils.toJson(new Result(1,"success"))).build();
    }

    public boolean isAuthAble() {
        return authAble;
    }

    public void setAuthAble(boolean authAble) {
        this.authAble = authAble;
    }

    static class Result{

        public Result(int code) {
            this.code = code;
        }


        public Result(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @SerializedName("M1")
        private int code;
        @SerializedName("M2")
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
