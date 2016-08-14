import com.facishare.common.web.auth.FSAuthDecryptedResult;
import com.facishare.common.web.auth.FSAuthTicketDecryptor;
import com.facishare.contract.common.ContractZipUtils;
import com.facishare.contract.common.JsonUtils;
import com.facishare.contract.MultiPartFeature;
import com.facishare.contract.core.model.ContractPro;
import com.google.common.collect.Lists;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aaron on 16/4/20.
 */
public class ContractHttpTest {
    interface Env {
        int LOCAL = 0, FTE = 2, FTE2 = 4, SDE = 3, FSCEISHI = 1;
    }

    static int evn = Env.LOCAL;
    static String url;
    static byte[] datas;

    static {
        switch (evn) {
            case Env.LOCAL:
                url = "http://localhost:8081/fs-contract";
                break;
            case Env.SDE:
                url = "http://172.31.103.134:8010/fs-contract";
                break;
            case Env.FTE:
                break;
            case Env.FTE2:
                break;
            case Env.FSCEISHI:
                url = "http://172.31.160.160:8005/FSC/EM/Contract/Upload";
                break;
        }
    }


    @Before
    public void setUp() {
        try {
            List<ContractPro> contracts = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                ContractPro con = new ContractPro();
                con.setCompany("fs");
                con.setContractId(i + "");
                con.setName("i--" + i);
                con.setTimesContacted("10");
                con.setPhoneNumbers(Lists.newArrayList("15110120310", "15110120312"));
                contracts.add(con);
            }
            datas = ContractZipUtils.compress(JsonUtils.toJson(contracts).getBytes("utf-8"));
        } catch (Exception e) {
            System.err.println("set up error");
        }
    }


    @Test
    public void upload() throws Exception {

        try {
            List<ContractPro> contracts = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                ContractPro con = new ContractPro();
                con.setCompany("fs");
                con.setContractId(i + "");
                con.setName("i--" + i);
                con.setTimesContacted("10");
                con.setPhoneNumbers(Lists.newArrayList("15110120310", "15110120312"));
                contracts.add(con);
            }
            datas = ContractZipUtils.compress(JsonUtils.toJson(contracts).getBytes("utf-8"));
        } catch (Exception e) {
            System.err.println("set up error");
        }

        Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();
        WebTarget target = client.target(url + "/EM/Contract/Upload");
        final StreamDataBodyPart filePart = new StreamDataBodyPart("data", new ByteArrayInputStream(datas), "data");
        final MultiPart multipart = new FormDataMultiPart().bodyPart(filePart);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).cookie("FSAuthX", "0G607EaDN040002v0YOb9m58STjBXQMWsnEPrHbBAJ3V4KWonFsf97lngGriaDLHIpQt1xHa1YW12YDTzrplyzt1RR82xs9QRrKbwVyfrmbgdyydORY5Te08JRgVpzzN1HtzDlLmnoescJ2DV9eeW75JHOOFA4zhrHphFoapIUQzQy8WG5RUuwjyq5MzbfQTqN0RaEeAxsIwcc7UM").post(Entity.entity(multipart, multipart.getMediaType()));
        Assert.assertEquals(Response.Status.OK, response.getStatus());
        Assert.assertNotNull(JsonUtils.fromJson(response.readEntity(String.class), HashMap.class).get("M2").equals("success"));
    }

    @Test
    public void readContract() {
        String content = ContractZipUtils.getGZipContentJson(ContractHttpTest.class.getResource("contacts_test.gz").getFile());
        System.out.println(content);
    }
    private static final String aesKey = "nirtHUNF/Ct8J7sf40VaIQui0N5r8gcbxGXKxRhu1C4=";
    private static final String aesIv = "jwNz4Ia8OHVpPyEXIQjJ2g==";

    private FSAuthTicketDecryptor fsAuthTicketDecryptor;
    {
        fsAuthTicketDecryptor= new FSAuthTicketDecryptor(aesKey, aesIv);
    }
    @Test
    public void cookieTest(){
        String FSAuthXC="0G60K2dqMm40000YoMS7yYc4lrVpYoHiziOHgeO4rrHGQY73VDKuURhthznfSbC2GoN5uDkDqqyHQ0hyNuljA1IyDAKTaDg68vDe4OxyCvAATHzkYz1KyuKXiOpzDT4xD6V2jz7PulWPyE5RpDOXa4yHGrW1XzUyL5xtxAptN3qF1bUNx1Zt42zApUmyovDL7izSI3RB3gfG8oFy2vSpcSq0SazagOpUsYFyj5sM2bbmrF4bwkPEogCN3K7ocQWikG3FLCuj2eyCdQOR7r6";
        FSAuthDecryptedResult result = fsAuthTicketDecryptor.decryptAuthTicketString(FSAuthXC);
        if (!result.getStatus().equals(FSAuthDecryptedResult.FSAuthDescryptedStatus.Success)) {
            System.out.println(result);
            return;
        }
    }
}
