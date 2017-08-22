package com.opensource;

import com.google.common.collect.Maps;
import com.opensource.functionstudy.Person;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by Aaron on 09/03/2017.
 */
public class TaskHandlerManager {

    private Person person;

    private final static Map<String,TaskHandler> handlers=Maps.newHashMap();

    @PostConstruct
    public void init(){
    }

    public TaskHandler getHandler(String t){

        return handlers.get(t);

    }

    enum TaskHandlers implements TaskHandler{

        ActionTask {
            @Override
            public void process() {



            }
        },
        CreateTask {
            @Override
            public void process() {

            }
        };

    }


}
