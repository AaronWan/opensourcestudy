package com.facishare.contract.core.service;

import com.facishare.contract.core.service.impl.ShareStoragePathManagerImpl;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Aaron on 16/4/19.
 */
public class ShareStoragePathManagerTest {
    private ShareStoragePathManagerImpl shareStoragePathManager;
    @Before
    public void setUp(){
        shareStoragePathManager=new ShareStoragePathManagerImpl();
        shareStoragePathManager.setConfigName("fs-contract-shared-storage-path");
        shareStoragePathManager.init();
    }
    @Test
    public void getShareStoragePath(){
        Map<String,Integer> map= Maps.newHashMap();
        for(int i=0;i<10000;i++){
            String path=shareStoragePathManager.getShareStoragePath();
            if(map.containsKey(path)){
                map.computeIfPresent(path,(key,value)->{
                    return value+1;
                });
            }else{
                map.put(path,1);
            }
        }
        System.out.println(map);
    }
}
