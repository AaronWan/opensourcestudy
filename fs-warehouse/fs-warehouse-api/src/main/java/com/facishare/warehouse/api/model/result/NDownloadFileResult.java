package com.facishare.warehouse.api.model.result;


import com.facishare.warehouse.api.model.base.ProtoBase;
import io.protostuff.Tag;

/**
 * Created by Aaron on 15/12/15.
 */
public class NDownloadFileResult extends ProtoBase {
  @Tag(1)
  private byte[] data;

  public NDownloadFileResult() {
  }

  public NDownloadFileResult(byte[] data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "NDownloadFileResult{" + "data.length=" + (data == null ? 0 : data.length) + '}';
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
}
