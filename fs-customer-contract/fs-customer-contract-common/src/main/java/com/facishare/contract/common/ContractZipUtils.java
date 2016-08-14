package com.facishare.contract.common;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Aaron on 16/4/19.
 */
public class ContractZipUtils {
    private static final String charset = "utf-8";

    public static String getGZipContentJson(File file) {
        GZIPInputStream gzi = null;
        ByteArrayOutputStream bos = null;
        byte[] buf = null;
        String result = "";
        try (FileInputStream fis = new FileInputStream(file)) {
            gzi = new GZIPInputStream(fis);
            bos = new ByteArrayOutputStream();
            int count = 0;
            byte[] tmp = new byte[2048];
            while ((count = gzi.read(tmp)) != -1) {
                bos.write(tmp, 0, count);
            }
            buf = bos.toByteArray();
            result = new String(buf, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (gzi != null)
                    gzi.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static String getGZipContentJson(String path) {
        return getGZipContentJson(new File(path));
    }

    public static String getGZipContentJson(byte[] gzip) {
        GZIPInputStream gzi = null;
        ByteArrayOutputStream bos = null;
        byte[] buf = null;
        String result = "";
        try (ByteArrayInputStream fis = new ByteArrayInputStream(gzip)) {
            gzi = new GZIPInputStream(fis);
            bos = new ByteArrayOutputStream();
            int count = 0;
            byte[] tmp = new byte[2048];
            while ((count = gzi.read(tmp)) != -1) {
                bos.write(tmp, 0, count);
            }
            buf = bos.toByteArray();
            result = new String(buf, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (gzi != null)
                    gzi.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static String saveZipFile(String dir, byte[] data, String protoName) {
        String fileName = getZipFileName();
        String filePath = dir + File.separator + fileName + "_" + protoName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            IOUtils.write(data, fos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    // 压缩
    public static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(data);
        gzip.close();
        return out.toByteArray();
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

    private static String getZipFileName() {
        Date date = new Date();
        String fileName =
                String.format("%s_%s", dateFormat.format(date), UUID.randomUUID().toString());
        return fileName;
    }
}
