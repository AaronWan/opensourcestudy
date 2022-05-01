package com.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogTest {
    @Test
    public void testLogPer1m() {
        for (;;) {
            log.error("test ...企业ID: 743676\n" +
                    "企业EA: 743676_sandbox\n" +
                    "企业名称: 高顿咨询-测试环境（743676_sandbox）\n" +
                    "回款明细: OrderPaymentObj\n" +
                    "数据ID: 6269ffaf384edd00014b0184\n" +
                    "数据名称: 20220428-105546生命状态: normal\n" +
                    "743676 OrderPaymentObj 6269ffaf384edd00014b0184");
        }
    }
}
