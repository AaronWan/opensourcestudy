package com.opensource.rest.proxy.model;

import lombok.Data;

/**
 * Created by Aaron on 27/12/2016.
 */
@Data
public class Page {
    private Boolean isByLastId;
    private String lastId;
    private int pageSize;
    private int pageNumber;
}
