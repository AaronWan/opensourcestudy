package com.opensource.volocity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.oracle.tools.packager.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 16/8/11.
 */
public class Test {
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        Template template = velocityEngine.getTemplate("test.vm");

        VelocityContext ctx = new VelocityContext();
        List<Map<String,String>> list= Lists.newArrayList();
        for(int i=0;i<10;i++){
            Map<String,String> person= Maps.newConcurrentMap();
            person.put("name","name"+i);
            person.put("age",""+i);
            list.add(person);
        }
        ctx.put("size", list.size());
        ctx.put("persons",list);
        try {
            FileWriter fileWriter = new FileWriter("xx");
            template.merge(ctx,fileWriter);
            fileWriter.flush();
            System.out.println(new String(IOUtils.readFully(new File("xx"))));
        } catch (Exception e) {

        }
    }
}
