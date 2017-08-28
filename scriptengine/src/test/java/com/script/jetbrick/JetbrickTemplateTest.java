package com.script.jetbrick;

import com.google.common.collect.Maps;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import jetbrick.util.JSONUtils;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class JetbrickTemplateTest {
    @Test
    public void testValue() {
        String expression = "${AccountObj.Lean.name}";
        Map<String, Object> data = getData();
        System.out.println(JSONUtils.toJSONString(data));
        JetEngine engine = JetEngine.create();
        JetTemplate template = engine.createTemplate(expression);

        StringWriter writer = new StringWriter();
        template.render(data, writer);

        System.out.println(writer.toString());
    }


    public Map<String, Object> getData() {
        Map<String, Object> datas = Maps.newHashMap();
        datas.put("AccountObj.name", "account name");
        datas.put("AccountObj.Lean.name", "leanName");
        datas.put("AccountObj.Lean.age", 60);


        return transfer(datas);
    }

    private Map<String, Object> transfer(Map<String, Object> datas) {
        /**
         * Account:
         *       name
         *       Lean:
         *             name
         *             age
         */
        Map<String, Object> rst = Maps.newConcurrentMap();
        datas.forEach((k, v) -> {
            String[] ss = k.split("\\.");
            if (ss.length == 3) {
                Map<String, Object> main = (Map<String, Object>) rst.get(ss[0]);
                HashMap<String, Object> child;
                if (main == null) {
                    main = Maps.newHashMap();
                    rst.put(ss[0], main);
                }
                child = (HashMap<String, Object>) main.get(ss[1]);
                if (child == null) {
                    main.put(ss[1], child = Maps.newHashMap());
                } else {
                    child = (HashMap<String, Object>) main.get(ss[1]);
                }
                child.put(ss[2], v);

            } else if (ss.length == 2) {
                Map<String, Object> main = (Map<String, Object>) rst.get(ss[0]);
                if (main == null) {
                    main = Maps.newHashMap();
                    rst.put(ss[0], main);
                }
                main.put(ss[1], v);
            }
        });
        return rst;
    }
}
