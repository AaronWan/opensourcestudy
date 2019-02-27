package com.junit.runner.test;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/2/26
 * @creat_time: 16:06
 * @since 6.4
 */
public class DataProcessor {
    @Data
    class DataModel{
        private ValueModel Value;
    }
    @Data
    class ValueModel{
        //        UDSText1__c
        private List<RequiredData> dataList;
    }

    @Data
    class RequiredData{
        private String tenantId;
        private String object_describe_api_name;
        private String _id;
        private String name;
        private String account_id__r;
        private String UDSText1__c;
    }
    static List<Integer> UDSText1__c= Lists.newArrayList(107286, 107382, 107690, 112378, 112555, 112744, 113148, 117112, 117808, 117948, 118105, 120055, 120845, 124017, 124285, 124446, 124544, 124852, 125333, 125530, 125824, 125863, 125872, 125937, 126018, 126077, 126301, 126506, 126658, 126867, 126951, 127189, 127195, 127196, 127281, 127433, 127462, 127548, 127549, 127579, 127676, 127677, 127736, 127745, 127800, 127801, 127802, 127840, 127847, 127856, 127857, 127858, 127867, 127869, 127875, 127876, 127909, 127912, 127914, 127917, 127923);
    public static void main(String[] args) throws IOException {
        Gson gson=new GsonBuilder().create();
        List<RequiredData> dataResult=Lists.newArrayList();
        File[] files=new File("/Users/Aaron/Desktop/todo_data").listFiles();
        Arrays.sort(files);
        for(File file:files){
            byte[] data = IOUtils.toByteArray(new FileInputStream(file));
            DataModel dataModel=gson.fromJson(new String(data),DataModel.class);
            dataResult.addAll(dataModel.getValue().getDataList().stream().filter((item)->!UDSText1__c.contains(Integer.valueOf(item.getUDSText1__c()+""))).collect(
                Collectors.toList()));
        }
        for (int i = 0; i < dataResult.size(); i++) {
            RequiredData item = dataResult.get(i);
            System.out.println("1,"+item.object_describe_api_name+","+item.name+","+item.account_id__r+","+item._id);
        }
    }
}
