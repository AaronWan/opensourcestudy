package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;

/**
 * Created by Aaron on 27/06/2017.
 */
public class BackOperateTypeTest {
    public static void main(String[] args) {
        Gson gson=new GsonBuilder().create();
        String qn="{title:\"test\",type:\"qixinNotify\",content:\"content\"}";
        OperateType operateType=gson.fromJson(qn,OperateType.class);
        System.out.println(operateType.getType());

    }
}
