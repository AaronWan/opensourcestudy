package com.facishare.contract.rest.provider;

import com.facishare.common.web.auth.FSAuthDecryptedResult;
import com.facishare.common.web.auth.FSAuthTicketDecryptor;
import com.facishare.contract.core.model.AuthInfo;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Aaron on 16/4/20.
 */
@Provider
@Component
public class ContractRestRequestFilter implements ContainerRequestFilter ,DynamicFeature {
    Logger LOG= LoggerFactory.getLogger(ContractRestRequestFilter.class);
    private static final String aesKey = "nirtHUNF/Ct8J7sf40VaIQui0N5r8gcbxGXKxRhu1C4=";
    private static final String aesIv = "jwNz4Ia8OHVpPyEXIQjJ2g==";

    private FSAuthTicketDecryptor fsAuthTicketDecryptor;
    {
        fsAuthTicketDecryptor= new FSAuthTicketDecryptor(aesKey, aesIv);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Map<String, Cookie> cookies = requestContext.getCookies();
        LOG.debug("-------------cookies------------");
        LOG.debug("cookies:{}",cookies);
        LOG.debug("--------------------------------");

        LOG.debug("-------------headers------------");
        MultivaluedMap<String, String> maps= requestContext.getHeaders();
        for (String name :maps
               .keySet()) {
            LOG.debug("name:{},value:{}",name,maps.get(name));
        }
        LOG.debug("--------------------------------");
        ResteasyProviderFactory.pushContext(AuthInfo.class, null);
        Cookie cookie=cookies.get("FSAuthX");
        if (cookie == null) {
            cookie = cookies.get("FSAuthXC");
        }
        if (cookie == null) {
            LOG.warn("[authorizeByCookieValue] [fail] [can't find FSAuthX/FsAuthXC cookie] [cookie:{}] [cookieInHeader:{}]",
                    cookies, requestContext.getHeaders());
            return;
        }
        String cookieValue = cookie.getValue();
        FSAuthDecryptedResult result = fsAuthTicketDecryptor.decryptAuthTicketString(cookieValue);
        if (!result.getStatus().equals(FSAuthDecryptedResult.FSAuthDescryptedStatus.Success)) {
            LOG.warn("[authorizeByCookieValue] [fail] [result:{}] [cookieValue:{}]", result, cookieValue);
            return;
        }
        String[] tokens = result.getPlainData().split("\\|");
        AuthInfo authInfo=AuthInfo.getAuthInfo(tokens);
        LOG.debug("AuthInfo:{}",authInfo);
        ResteasyProviderFactory.pushContext(AuthInfo.class,authInfo);
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        AuthInfoType authType = resourceInfo.getResourceMethod().getAnnotation(AuthInfoType.class);
        if(authType == null) return;
        LOG.debug("config auth filter for " + resourceInfo.getResourceMethod().getName());

        ContractRestRequestFilter p = new ContractRestRequestFilter();
        context.register(p);
    }
}
