package com.facishare.contract.rest;

import com.facishare.contract.core.model.AuthInfo;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by Aaron on 16/4/19.
 */

public interface IContractService {

    Response upload(MultipartFormDataInput input, @Context AuthInfo authInfo);
}
