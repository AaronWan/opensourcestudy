package com.facishare.contract.core.action;

import com.facishare.contract.common.ContractZipUtils;
import com.facishare.contract.common.JsonUtils;
import com.facishare.contract.core.facade.ContractAction;
import com.facishare.contract.core.model.AuthInfo;
import com.facishare.contract.core.model.ContractPro;
import com.facishare.contract.core.service.impl.ShareStoragePathManagerImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 16/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:warehouse-core.xml"})
public class ContractActionTest {
    @Autowired
    private ContractAction contractAction;

    @Test
    public void saveContract() throws Exception {
        String filePath=ContractActionTest.class.getResource("/contract.gz").getFile();
        contractAction.saveContract(new AuthInfo(),
                createJsonFile(10));
    }

    public byte[] createJsonFile(int n) throws Exception {
        List<ContractPro> contracts = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            ContractPro con = new ContractPro();
            con.setCompany("fs");
            con.setContractId(i + "");
            con.setDeleted(1);
            con.setName("测试i--" + i);
            con.setTimesContacted("10");
            con.setPhoneNumbers(Lists.newArrayList("15110120310", "15110120312","17162716273"));
            contracts.add(con);
        }
        byte[] datas=JsonUtils.toJson(contracts).getBytes("utf-8");
        System.err.println(datas.length/(1024*1024));
        return ContractZipUtils.compress(datas);
    }
}
