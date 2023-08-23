package com.approvalflow;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wansong
 * @date 2023/6/9
 * @apiNote
 **/
@Data
public class Workflow {
    String name;
    private List<Node> activities;
    private List<Transition> transitions;
    private Map<String, Node> activityMap;
    private Map<String, Transition> transitionMap;

    public void init() {
        activityMap = activities.stream().collect(Collectors.toMap(node -> node.getId(), node -> node));
        transitionMap = transitions.stream().peek(item -> {
            item.setFrom(activityMap.get(item.getFromId()));
            item.setTo(activityMap.get(item.getToId()));
        }).collect(Collectors.toMap(transition -> transition.getId(), transition -> transition));
    }

    public List<Transition> findCurrentActivityIdTo(String fromId) {
        List<Transition> result = Lists.newArrayList();
        for (Transition transition : transitions) {
            if (transition.getFromId().equals(fromId)) {
                Node node = transition.to;
                String action = getAction(transition);
                if("go_back".equals(action)||"reject".equals(action)){
                    continue;
                }
                System.out.println(fromId + " " + node.getId()+" "+action);
                if (StringUtils.isBlank(node.getName())) {
                    result.addAll(findCurrentActivityIdTo(transition.toId));
                } else {
                    result.add(transition);
                }
            }
        }
        return result;
    }

    private String getAction(Transition transition) {
        if (Objects.isNull(transition.condition)) {
            return "";
        }
        try {
            return (String) ((LinkedTreeMap) (((LinkedTreeMap.Entry) ((transition.condition).entrySet().toArray()[2])).getValue())).values().stream().findFirst().orElse(null);
        } catch (Exception e) {
            return "";
        }
    }

    public List<Transition> findCurrentActivityIdFrom(String id) {
        List<Transition> result = Lists.newArrayList();
        for (Transition transition : transitions) {
            if (transition.getToId().equals(id)) {
                Node node = transition.from;
                if (StringUtils.isBlank(node.getName())) {
                    result.addAll(findCurrentActivityIdFrom(transition.fromId));
                } else {
                    result.add(transition);
                }
            }
        }
        return result;
    }

    @SneakyThrows
    public static void main(String[] args) {
        FileInputStream fis = new FileInputStream(Workflow.class.getResource("/definition.json").getPath());
        String content = IOUtils.toString(fis);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Workflow workflow = gson.fromJson(content, Workflow.class);
        workflow.init();
        gson.toJson(workflow.findCurrentActivityIdTo("1658104272890"));
    }
}
