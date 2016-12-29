package com.opensource.rest.proxy.doc;

import com.google.common.collect.Lists;
import com.opensource.rest.proxy.common.JsonUtil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 29/12/2016.
 */
@Getter
@Setter
public class DocModel {
    private List<Clazz> clazzes=new ArrayList<>();
    public DocModel addClazz(Clazz clazz){
        clazzes.add(clazz);
        return this;
    }
    @Override
    public String toString() {
        return JsonUtil.toPrettyJson(this);
    }
}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Clazz extends BaseModel{
    public List<ResourceMethod> methods= Lists.newArrayList();
    public Clazz addMethod(ResourceMethod restUri){
        methods.add(restUri);
        return this;
    }
}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ResourceMethod extends BaseModel{
    private String arg;
    private String result;

    public ResourceMethod(String name,String desc,String arg,String result){
        this.setName(name);
        this.setDesc(desc);
        this.setArg(arg);
        this.setResult(result);
    }
}
@Getter
@Setter
class BaseModel{
     public String name;
     public String desc;

    @Override
    public String toString() {
        return JsonUtil.toPrettyJson(this);
    }
}