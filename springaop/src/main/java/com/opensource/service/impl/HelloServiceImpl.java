package com.opensource.service.impl;

import com.opensource.service.HelloService;

/**
 * Created by Aaron on 27/04/2017.
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello(String hello) {
        return "result----------->"+hello;
    }
}
