package com.facishare.ffmpeg.common;

import it.sauronsoftware.jave.MultimediaInfo;

/**
 * Created by Aaron on 2016/10/9.
 */
public class ConvertResult {
    public boolean success=true;
    public MultimediaInfo info;
    public byte[] data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MultimediaInfo getInfo() {
        return info;
    }

    public void setInfo(MultimediaInfo info) {
        this.info = info;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
