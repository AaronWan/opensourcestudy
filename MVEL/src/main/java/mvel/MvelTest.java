package mvel;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mvel2.templates.TemplateRuntime;

import java.util.HashMap;
import java.util.Map;

/**
 * https://cloud.tencent.com/developer/article/2048242
 */
public class MvelTest {
    public static void main(String[] args) {
        String template = "Hello, my name is @{name.toUpperCase()}";
        Map vars = new HashMap();
        vars.put("name", "Michael");

        String output = (String) TemplateRuntime.eval(template, vars);
        System.out.println(output);

        template = "@{a+b}";
        vars = new HashMap();
        vars.put("a", 1);
        vars.put("b", 2);

        Integer rst = (Integer) TemplateRuntime.eval(template, vars);
        System.out.println(rst);


        template = "Hello, my name is @{k.name}";
        Map person= Maps.newHashMap();
        person.put("name","abc");
        vars = new HashMap();
        vars.put("k", person);

        output = (String) TemplateRuntime.eval(template, vars);
        System.out.println(output);
    }

    @AllArgsConstructor
    @Getter
    static class Person {
        String name;
    }
}
