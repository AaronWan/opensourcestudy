package com.facishare.fsc.common.utils;


import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    public static byte[] encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
        try {
            //消除乱码
            contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height, hints);
            return transferTobytes(bitMatrix);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encode(String contents, int width, int height) {
        Map<EncodeHintType, Integer> hints = Maps.newHashMap();
        hints.put(EncodeHintType.MARGIN, 0);
        return QRCodeUtils.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
    }

    private static byte[] transferTobytes(BitMatrix matrix) {
        BufferedImage image = toBufferedImage(matrix);
        return ImageUtils.getBufferedImage(image);
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

}
