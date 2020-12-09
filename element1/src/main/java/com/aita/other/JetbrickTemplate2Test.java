package com.aita.other;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
/**
public class JetbrickTemplate2Test {
    @Test
    public void testValue() {
        String expression = "${AccountObj.Temp.Temp2.Temp3.name}";
        Map<String, Object> data = getData();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(data));
        JetEngine engine = JetEngine.create();
        JetTemplate template = engine.createTemplate(expression);

        StringWriter writer = new StringWriter();
        template.render(data, writer);

        System.out.println(writer.toString());
    }
    **/
/**

    public Map<String, Object> getData() {
        Map<String, Object> datas = new HashMap<>();
        datas.put("AccountObj.Lean.name", "leanName");
        datas.put("AccountObj.Lean.age", 60);
        datas.put("AccountObj.name", "account name");
        datas.put("AccountObj.Temp.name", "zhangsan");
        datas.put("AccountObj.Temp.Temp2.name", "lisi");
        datas.put("AccountObj.Temp.Temp2.Temp3.name", "wangwu");


        return transfer(datas);
    }

    private Map<String, Object> transfer(Map<String, Object> datas) {
        Map<String, Object> rst = new HashMap<>();
        datas.keySet().forEach(key -> setValue(rst, key, datas.get(key)));
        /**
         * Account:
         *       name
         *       Lean:
         *             name
         *             age
         */

 /**       return rst;
    }

    private void setValue(Map<String, Object> rst, String key, Object o) {
        String[] temp = key.split("\\.");
        Map<String, Object> lastObj = (Map<String, Object>) rst.get(temp[0]);
        if (lastObj == null) {
            rst.put(temp[0], lastObj = new HashMap());
        }
        for (int i = 1; i < temp.length - 1; i++) {
            if (lastObj.get(temp[i]) == null) {
                Map<String, Object> newObj = new HashMap<>();
                lastObj.put(temp[i], newObj);
                lastObj = newObj;
            } else {
                lastObj = (Map<String, Object>) lastObj.get(temp[i]);
            }
        }

        lastObj.put(temp[temp.length - 1], o);
    }
}
**/