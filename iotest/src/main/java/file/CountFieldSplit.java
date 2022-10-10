package file;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import file.flow.SlowTest;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CountFieldSplit {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> eis= Lists.newArrayList("713386","730183","715464","724966","721192","724835","721818","488989","724968","719651","692231","671584","720590","723447","684211","713108","718665","716426","705880","710930","711988","682722","83733","452039","683002","728783","728122","458505","732025","391146","733452","732335","735110","159800","678828","736536","671149","650993","730112","735987","744915","738543","741523","748839","409711","713119","677184","75170","738886","744110","732508","558400","751061","725806","327520","750508","42071","729737","665236","750792","738938","710553");
        FileUtil.readFileByLine(new FileInputStream(SlowTest.class.getResource("/whitelist").getPath()), item->{
            if(StringUtils.isNotBlank(item)){
                List<String> data = Splitter.on("\t").splitToList(item);
                String tenantId=data.get(0);
                String owner=data.get(1);
                String name=data.get(2);
                String level=data.get(3);
                if(!eis.contains(tenantId)){
                    return;
                }
                File definition = new File("/Users/Aaron/Documents/code/fxiaoke/opensourcestudy/iotest/src/main/resources/data/definition_" + tenantId+".txt");
                if(definition.exists()){
                    StringBuilder result=new StringBuilder(tenantId+" "+name);
                    Map<String,List<String>> entityIdFields= Maps.newHashMap();
                    FileUtil.readFileByLine(definition.getPath(),line->{
                        List<String> def = Splitter.on("|").splitToList(line);
                        if(def.size()==10){
                            String field=def.get(9);
                            String entityId=def.get(4);
                            if(field.contains("count")){
                                entityIdFields.computeIfAbsent(entityId, (Function) o -> new ArrayList<>()).add(field);
                            }
                        }
                    });
                    result.append(entityIdFields);
                    System.out.println(result);
                }
            }
        });
    }
}
