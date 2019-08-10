package com.facishare.fsc.common.utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Aaron on 16/5/3.
 */
public class QRCodeUtils {
  /**
   * 生成QRCode二维码<br>
   * 在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
   * static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
   * 修改为UTF-8，否则中文编译后解析不了<br>
   */
  public static byte[] encode(String contents,
                              BarcodeFormat format,
                              int width,
                              int height,
                              Map<EncodeHintType, ?> hints) {
    try {
      //消除乱码

      contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
      BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height, hints);
      //bitMatrix = deleteWhite(bitMatrix);
      return transferTobytes(bitMatrix);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] encode(String contents, int width, int height) {
    Map<EncodeHintType, Object> hints = new Hashtable<>();
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
    hints.put(EncodeHintType.MARGIN, 0);
    return QRCodeUtils.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
  }

  private static byte[] transferTobytes(BitMatrix matrix) throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    Graphics2D g = image.createGraphics();
    g.drawImage(image, null, null);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(image, "png", byteArrayOutputStream);
    g.dispose();
    byte[] pngBytes=byteArrayOutputStream.toByteArray();
    byteArrayOutputStream.close();
    return pngBytes;
  }

  private static BitMatrix deleteWhite(BitMatrix matrix) {
    int[] rec = matrix.getEnclosingRectangle();
    int resWidth = rec[2] + 1;
    int resHeight = rec[3] + 1;
    BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
    resMatrix.clear();
    for (int i = 0; i < resWidth; i++) {
      for (int j = 0; j < resHeight; j++) {
        if (matrix.get(i + rec[0], j + rec[1])) {
          resMatrix.set(i, j);
        }
      }
    }
    return resMatrix;
  }

  /**
   * 生成二维码内容<br>
   *
   * @param matrix
   * @return
   */
  private static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) == true ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
      }
    }
    return image;
  }

  public static void main(String[] args) throws IOException {
//    String qr = "https://www.fxiaoke.com/mob/qx/qrcode.html?token=81834936903040749511131c0464ff44";
//    File file1 = new File("/Users/liuq/Downloads/qr1.png");
//    if (file1.exists()) {
//      file1.delete();
//    }
//    byte[] qrByte = QRCodeUtils.encode(qr, 200, 200);
//    FileUtils.writeByteArrayToFile(file1, qrByte);
  }
}
