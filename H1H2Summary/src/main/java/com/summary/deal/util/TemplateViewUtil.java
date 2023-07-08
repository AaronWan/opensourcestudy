package com.summary.deal.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author wansong
 * @date 2023/6/4
 * @apiNote
 **/
@Slf4j
@UtilityClass
public class TemplateViewUtil {

    static VelocityEngine engine = new VelocityEngine();

    static {
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();
    }


    public String renderHtml(String templatePath, Map bindings) {
        Template template = engine.getTemplate(templatePath, StandardCharsets.UTF_8.name());
        StringWriter writer = new StringWriter();
        VelocityContext context = new VelocityContext();
        bindings.forEach((k, v) -> context.put((String) k, v));
        template.merge(context, writer);
        return writer.toString();
    }
}
