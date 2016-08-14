package com.facishare.contract.core.service;

import org.mongodb.morphia.annotations.PostLoad;

import javax.annotation.PostConstruct;

/**
 * Created by Aaron on 16/4/19.
 */
public interface ShareStoragePathManager {
    @PostConstruct
    void init();

    String getShareStoragePath();
}
