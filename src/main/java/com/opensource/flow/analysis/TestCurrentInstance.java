package com.opensource.flow.analysis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aaron on 16/02/2017.
 */
public class TestCurrentInstance {
    private Workflow workflow;
    private Instance instance;
    public static final String START = "start", END = "end";

    @Before
    public void init() {
        workflow = new Workflow().addTranstion(START, 1).addTranstion(1, "a").addTranstion("a", 2).addTranstion("a", 3)
                .addTranstion(3, "b").addTranstion("b", 7).addTranstion(7, END).addTranstion("a", 4).addTranstion(5, 6)
                .addTranstion(6, "c").addTranstion("c", 4);
        instance = new Instance().add(START).add(1).add(2).add(4).add(5).add(3).add(6).add(4).add(5).add(6).add(7).add(8);
    }

    @Test
    public void test() {
        Node root = null;
        for (int i = 0; i < workflow.getTransitions().size(); i++) {
            Transition transition = workflow.getTransitions().get(i);
            Object from = transition.getFrom();
            Object to = transition.getTo();
            if (root == null) {
                root = new Node(from);
            }
            setNode(root, from, to);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(root));
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
}
