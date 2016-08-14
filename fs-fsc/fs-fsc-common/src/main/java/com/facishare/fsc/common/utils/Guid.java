package com.facishare.fsc.common.utils;

import java.util.UUID;

/**
 * Created by Aaron on 15/12/21.
 */
public class Guid {
    public static String getGuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
