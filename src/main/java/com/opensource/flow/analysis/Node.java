package com.opensource.flow.analysis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 16/02/2017.
 */
public class Node {
    private List<Node> preNode = Lists.newArrayList();
    private List<Node> nextNode = Lists.newArrayList();
    private Map<Object,Node> allPreNodes = Maps.newHashMap();
    private Map<Object,Node> allNextNodes = Maps.newHashMap();
    private Object activityId;

    public Node(Object activityId){
        this.activityId=activityId;
    }
    public Node setPreNode(Node node){
        if(allPreNodes.get(node.getActivityId())!=null){
            return this;
        }
        preNode.add(node);
        allPreNodes.put(node.getActivityId(),node);
        node.setNextNode(this);
        return this;
    }
    public Node setNextNode(Node node){
        if(allNextNodes.get(node.getActivityId())!=null){
            return this;
        }
        nextNode.add(node);
        node.setPreNode(this);
        allNextNodes.put(node.getActivityId(),node);
        return this;
    }

    public List<Node> getPreNode() {
        return preNode;
    }

    public void setPreNode(List<Node> preNode) {
        this.preNode = preNode;
    }

    public List<Node> getNextNode() {
        return nextNode;
    }

    public void setNextNode(List<Node> nextNode) {
        this.nextNode = nextNode;
    }

    public Map<Object, Node> getAllPreNodes() {
        return allPreNodes;
    }

    public void setAllPreNodes(Map<Object, Node> allPreNodes) {
        this.allPreNodes = allPreNodes;
    }

    public Map<Object, Node> getAllNextNodes() {
        return allNextNodes;
    }

    public void setAllNextNodes(Map<Object, Node> allNextNodes) {
        this.allNextNodes = allNextNodes;
    }

    public Object getActivityId() {
        return activityId;
    }

    public void setActivityId(Object activityId) {
        this.activityId = activityId;
    }
}
