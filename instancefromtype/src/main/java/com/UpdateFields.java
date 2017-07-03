package com;

import lombok.Data;

import java.util.List;

/**
 * Created by Aaron on 27/06/2017.
 */
@BackOperateType(name="updates")
@Data
public class UpdateFields {
    private List<String> fields;
}
