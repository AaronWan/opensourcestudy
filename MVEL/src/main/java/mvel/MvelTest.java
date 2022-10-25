package mvel;

import org.mvel2.templates.TemplateRuntime;

import java.util.HashMap;
import java.util.Map;

public class MvelTest {
    public static void main(String[] args) {
        String template = "Hello, my name is @{name.toUpperCase()}";
        Map vars = new HashMap();
        vars.put("name", "Michael");

        String output = (String) TemplateRuntime.eval(template, vars);
        System.out.println(output);

        template = "@{a+b}";
        vars = new HashMap();
        vars.put("a",1);
        vars.put("b",2);

        Integer rst = (Integer) TemplateRuntime.eval(template, vars);
        System.out.println(rst);

    }
}
