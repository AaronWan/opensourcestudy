package com.opensource.flow.analysis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.experimental.UtilityClass;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


/**
 * Created by Aaron on 16/02/2017.
 */
public class WorkflowInstanceUtils {
    private Workflow workflow=new Workflow();
    private Instance instance=new Instance();

    public String caculate(Workflow workflow, Instance instance) {
        Node root = getRootNode(workflow);
        for (int i = 0; i < workflow.getTransitions().size(); i++) {
            Transition transition = workflow.getTransitions().get(i);
            Object from = transition.getFrom();
            Object to = transition.getTo();
            setNode(root, from, to);
        }
        final Node rootNode = root;
        List<Object> temp = Lists.newArrayList();
        instance.getActivityInstances().forEach(activityInstance -> {
            if (rootNode.getAllNextNodes().get(activityInstance) == null) {
                rootNode.inc();
            } else {
                rootNode.getAllNextNodes().get(activityInstance).inc();
                rootNode.getAllNextNodes().get(activityInstance).getAllNextNodes().forEach((k, v) -> {
                    temp.remove(k);
                });
            }

            temp.remove(activityInstance);
            temp.add(activityInstance);
        });
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < temp.size(); i++) {
            sb.append(temp.get(i) + "-");
            if (rootNode.getAllNextNodes().get(temp.get(i)) != null) {
                sb.append("(" + rootNode.getAllNextNodes().get(temp.get(i)).getCount() + "),");
            } else {
                sb.append("(" + rootNode.getCount() + "),");
            }
        }
        return sb.toString();
    }

    private Node getRootNode(Workflow workflow) {
        Set<String> noFromActivities= Sets.newTreeSet();
        workflow.getTransitions().forEach(transition -> {
            noFromActivities.add(transition.getFrom()+"");
            noFromActivities.add(transition.getTo()+"");
        });
        workflow.getTransitions().forEach(transition -> {
            noFromActivities.remove(transition.getTo());
        });
        if(noFromActivities.size()>1||noFromActivities.size()==0){
            throw new RuntimeException("没有开始节点，或节点配置有异常");
        }
        return new Node(""+noFromActivities.toArray()[0]);
    }

    public void setNode(Node node, Object from, Object to) {
        if (node.getActivityId().equals(from)) {
            node.setNextNode(new Node(to));
            return;
        }
        Node temp = node.getAllNextNodes().get(from);
        if (temp != null) {
            temp.setNextNode(new Node(to));
        }
    }


    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public void clear() {
        this.getInstance().getActivityInstances().clear();
        this.getWorkflow().getTransitions().clear();
    }
}
