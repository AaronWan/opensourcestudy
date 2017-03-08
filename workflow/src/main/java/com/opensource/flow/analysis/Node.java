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
    private List<String> instanceIds=Lists.newArrayList();
    private int count;

    public Node(Object activityId){
        this.activityId=activityId;
    }
    public Node setPreNode(Node node){
        if(allPreNodes.get(node.getActivityId())!=null){
            return this;
        }
        preNode.add(node);
        allPreNodes.put(node.getActivityId(),node);
        allNextNodes.forEach((k,v)->{v.setPreNode(node);});
        if(node.getAllNextNodes().get(this.getActivityId())==null){
            node.setNextNode(this);
        }
        return this;
    }
    public Node setNextNode(Node node){
        if(allPreNodes.get(node.getActivityId())!=null){
            return this;
        }
        nextNode.add(node);
        allNextNodes.put(node.getActivityId(),node);
        allPreNodes.forEach((k,v)->{v.setNextNode(node);});
        node.setPreNode(this);
        return this;
    }
    public void addInstanceId(String instanceId){
        this.instanceIds.add(instanceId);
    }

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void inc() {
        count++;
    }
}
