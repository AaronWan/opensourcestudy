package com.opensource.flow.analysis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Created by Aaron on 16/02/2017.
 */
public class TestCurrentInstance {
    private Workflow workflow;
    private Instance instance;
    public static final String START = "start", END = "end";

    @Before
    public void init() {
        workflow = new Workflow().addTranstion(START, 1).addTranstion("c", 4).addTranstion(1, "a").addTranstion("a", 2).addTranstion(2, 3)
                .addTranstion(3, "b").addTranstion("a", 9).addTranstion(10, 11).addTranstion(11, 7).addTranstion("b", 7).addTranstion(7, END).addTranstion("a", 4).addTranstion(4, 5).addTranstion(5, 6)
                .addTranstion(6, "c").addTranstion("c", "b").addTranstion("b", "7");
        instance = new Instance().add(START).add(1).add(2).add(4).add(9).add(11).add(5).add(3).add(6).add(4).add(5).add(6).add(4).add(5).add(6).add(7);
    }

    @Test
    public void test() {
        String line = null;
        System.out.println("1.wf put in transtion eg:1,2");
        System.out.println("2.i put in activity instance via time seq eg:1,2");
        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(scanner.hasNext())
                line = scanner.nextLine();
            while (line != null) {
                if (line.equals("wf")) {
                    cmd = "wf";
                    workflow = new Workflow();
                    continue;
                }
                if (line.equals("i")) {
                    cmd = line;
                    instance = new Instance();
                    continue;
                }
                if (line != null)
                    if (cmd.equals("wf")) {
                        String[] temps = line.split(",");
                        workflow.addTranstion(temps[0], temps[1]);
                        continue;
                    } else {
                        instance.getActivityInstances().addAll(Arrays.asList(line.split(",")));
                        caculate(workflow, instance);
                    }
            }
        }
    }

    @Test
    public void testMain(){
        Scanner s = new Scanner(System.in);
        System.out.println("请输入字符串：");
        while (true) {
            String line = s.nextLine();
            if (line.equals("exit")) break;
            System.out.println(">>>" + line);
        }
    }

    @Test
    public void testPreData() {
        caculate(workflow, instance);
    }

    public void caculate(Workflow workflow, Instance instance) {
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
        for (int i = 0; i < temp.size(); i++) {
            System.out.printf(temp.get(i) + "-");
            if (rootNode.getAllNextNodes().get(temp.get(i)) != null) {
                System.out.printf("(" + rootNode.getAllNextNodes().get(temp.get(i)).getCount() + ")\t");
            } else {
                System.out.printf("(" + rootNode.getCount() + ")\t");
            }
        }
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


    public void testBpmExtension() {
        Map<String, Object> bpmExtension = Maps.newHashMap();

    }
}
