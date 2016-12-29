package com.opensource.rest.proxy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Aaron on 29/12/2016.
 */
@Setter
@Getter
public class PageResult<E> {
    private int total;
    private List<E> result;
}
