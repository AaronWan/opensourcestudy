package com.opensource.rest.proxy.doc;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.opensource.rest.proxy.annotation.RestResource;
import com.opensource.rest.proxy.annotation.RestUri;
import com.oracle.tools.packager.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Aaron on 28/12/2016.
 */
public class RestProxyDocUtil {
    public static void main(String[] args) {
        new RestProxyDocUtil().createDoc("com.opensource.rest.proxy.resource");
    }

    public void createDoc(String packagePath) {
        Set<Class<?>> clazz = getResourceServiceClass(packagePath);
        DocModel docModel = getDocModel(clazz);
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        Template template = velocityEngine.getTemplate("test.vm");

        VelocityContext ctx = new VelocityContext();
        ctx.put("list",docModel.getClazzes());
        try {
            FileWriter fileWriter = new FileWriter("xx");
            template.merge(ctx,fileWriter);
            fileWriter.flush();
            System.out.println(new String(IOUtils.readFully(new File("xx"))));
        } catch (Exception e) {

        }
    }

    private DocModel getDocModel(Set<Class<?>> clazz) {
        DocModel docModel = new DocModel();
        clazz.forEach(zz -> {
            RestResource restResource = zz.getAnnotation(RestResource.class);
            Clazz temp = new Clazz();
            temp.setDesc(restResource.desc());
            temp.setName(restResource.value());
            docModel.addClazz(temp);
            Method[] methods = zz.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                RestUri uri = method.getAnnotation(RestUri.class);
                if (uri != null)
                    temp.addMethod(new ResourceMethod(uri.value(), uri.desc(), getDesc(method.getReturnType()), getDesc(method.getParameterTypes()[0])));
            }
        });
        return docModel;
    }

    private static Set<Class<?>> getResourceServiceClass(String packageScan) {
        Reflections reflections = new Reflections(packageScan);
        Set<Class<?>> ret = reflections.getTypesAnnotatedWith(RestResource.class);
        return ret;
    }

    private static String getDesc(Class clazz) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\n");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getType().getTypeName() + " " + fields[i].getName());
        }
        sb.append("\n}");
        return sb.toString();
    }
}
