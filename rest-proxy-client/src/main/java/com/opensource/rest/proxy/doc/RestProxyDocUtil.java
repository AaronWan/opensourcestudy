package com.opensource.rest.proxy.doc;

import com.google.common.collect.Lists;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Aaron on 28/12/2016.
 */
public class RestProxyDocUtil {
    public static void main(String[] args) {
        new RestProxyDocUtil().createDoc("com.opensource.rest.proxy.doc");
    }
    public void createDoc(String packagePath) {
        String packageDir=packagePath.replaceAll("\\.","/");
        System.out.println(packageDir);

        try{
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDir);
            List<Class> classes= Lists.newArrayList();
            while (dirs.hasMoreElements()){
                URL dir = dirs.nextElement();
                findAllClazzIndir(classes,dir.getPath());
            }
        }catch (Exception e){

        }

    }

    private void findAllClazzIndir(List<Class> classes,String path) throws ClassNotFoundException {
        File file=new File(path);
        if(file.isFile()){
            classes.add(Class.forName(path));
        }else{
            String[] files=file.list();
            for (int i = 0; i < files.length; i++) {
                findAllClazzIndir(classes,files[i]);
            }
        }

    }
}
