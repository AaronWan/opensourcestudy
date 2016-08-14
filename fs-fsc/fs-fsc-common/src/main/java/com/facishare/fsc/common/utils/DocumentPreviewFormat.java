package com.facishare.fsc.common.utils;

public enum DocumentPreviewFormat {
    Unknown(0),
    Image(1),
    Html(2),
    Text(3);
    private int value;

    DocumentPreviewFormat(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}