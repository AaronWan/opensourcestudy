package file.flow;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static file.FileUtil.readFileByLine;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/6/26
 * @creat_time: 12:14
 * @since 6.6
 */
public class SlowTest {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(SlowTest.class.getResource("/slow.log").getPath());
        List<LogBean> data = Lists.newArrayList();
        readFileByLine(fis, (line) -> {
            String[] temp = line.split(" ");
            if (!temp[5].equals("object_describe")) {
                data.add(LogBean.builder()
                        .time(temp[0])
                        .traceId(temp[1])
                        .duration(Integer.valueOf(temp[2]))
                        .udobj665(!temp[4].equals("30079"))
                        .entityId(temp[5])
                        .tenantId(temp[6])
                        .build());
            }
        });
        long gt = 3000;
        data.stream().filter(item -> !item.isUdobj665()).filter(item->item.duration>gt).forEach(item -> System.out.println(item));
//
//        System.out.println("------------------\n");
        Map<String, Map<String, Integer>> tenantIdAndEntityCount = Maps.newHashMap();
        data.stream().filter(item -> !item.isUdobj665()).filter(item -> item.duration > gt).forEach(item ->
                {
                    Map<String, Integer> exists = tenantIdAndEntityCount.get(item.getTenantId());
                    if (exists == null) {
                        tenantIdAndEntityCount.put(item.getTenantId(), exists = Maps.newHashMap());
                    }
                    Integer temp = exists.get(item.getEntityId());
                    if (temp == null) {
                        exists.put(item.getEntityId(), 1);
                    } else {
                        exists.put(item.getEntityId(), temp + 1);
                    }

                }
        );
        tenantIdAndEntityCount.forEach((k, v) -> {
            System.out.print(k + "\t");
            v.forEach((k1, v1) -> System.out.print(k1 + ":" + v1+","));
            System.out.println();
        });

    }

    @Builder
    @Data
    static class LogBean {
        String time;
        String traceId;
        Integer duration;
        String entityId;
        String tenantId;
        boolean udobj665;
    }
}
