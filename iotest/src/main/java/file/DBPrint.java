package file;

import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class DBPrint {
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("/Users/Aaron/Documents/code/fxiaoke/opensourcestudy/iotest/src/main/resources/db.json");
        String content = IOUtils.toString(fis);
        Map rst = new GsonBuilder().create().fromJson(content, Map.class);
        rst.forEach((k,v)->{
            Map data = (Map) ((Map)v).get("mongo");
            System.out.println(k+"|"+data.get("url")+"|"+data.get("db"));
        });

    }
}
