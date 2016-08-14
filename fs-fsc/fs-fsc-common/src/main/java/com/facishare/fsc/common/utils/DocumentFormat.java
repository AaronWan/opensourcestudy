package com.facishare.fsc.common.utils;

/**
 * Created by Aaron on 16/5/4.
 */
public enum  DocumentFormat {
    Unknown (0),
    Text (1),
    Image (2),
    Audio (3),
    Video (4),
    Word (101),
    Excel (102),
    Ppt (103),
    Pdf (104);

    private int value;

    DocumentFormat(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
