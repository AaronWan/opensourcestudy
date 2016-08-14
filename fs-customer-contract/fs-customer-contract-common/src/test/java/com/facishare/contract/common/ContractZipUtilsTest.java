package com.facishare.contract.common;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Aaron on 16/4/20.
 */
public class ContractZipUtilsTest {
    @Test
    public void getGZipContentJson() {
        String file = ContractZipUtilsTest.class.getResource("/contracts.gz").getFile();
        String result = ContractZipUtils.getGZipContentJson("/Users/Aaron/Desktop/test.gz");
        System.out.println(result);
        Assert.assertEquals(result, "test");
    }

    @Test
    public void saveZipFile() throws Exception {
        String file = ContractZipUtilsTest.class.getResource("/contracts.gz").getFile();
        String filePath = ContractZipUtils.saveZipFile(ContractZipUtilsTest.class.getResource("/").getFile(), IOUtils.toByteArray(new FileInputStream(file)), "contract.zip");
        String result = ContractZipUtils.getGZipContentJson(filePath);
        Assert.assertEquals(result, "test");
        new File(filePath).deleteOnExit();
    }
}
