package com.json.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public class FileUtils {
    public static String toString(String path)  {
        String configPath=FileUtils.class.getResource(path).getPath();
        byte[] bytes=new byte[1024];
        int l=0;
        try(FileInputStream fis=new FileInputStream(configPath)){
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            while ((l=fis.read(bytes))>0){
                baos.write(bytes,0,l);
            }
            String rst= new String(baos.toByteArray(), StandardCharsets.UTF_8);
//            System.out.printf("load:%s,content:%s\n",path,rst);
            return rst;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void write(String path, String content) {
//        System.out.printf("write config:%s,content:%s\n",path,content);

        String configPath=FileUtils.class.getResource(path).getPath();
        try(FileOutputStream fis=new FileOutputStream(configPath)){
            fis.write(content.getBytes(StandardCharsets.UTF_8));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
